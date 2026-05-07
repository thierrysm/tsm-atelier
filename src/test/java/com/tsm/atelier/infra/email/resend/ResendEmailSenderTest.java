package com.tsm.atelier.infra.email.resend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tsm.atelier.config.ApplicationProperties;
import com.tsm.atelier.domain.common.UserRegisteredEvent;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

@ExtendWith(MockitoExtension.class)
@DisplayName("ResendEmailSender")
class ResendEmailSenderTest {

  @Mock private RestClient resendRestClient;
  @Mock private ResendProperties resendProperties;
  @Mock private ApplicationProperties applicationProperties;

  @InjectMocks private ResendEmailSender sender;

  private RestClient.RequestBodyUriSpec uriSpec;
  private RestClient.RequestBodySpec bodySpec;
  private RestClient.ResponseSpec responseSpec;

  private final UserRegisteredEvent event =
      new UserRegisteredEvent("ronaldinho@email.com", "tk-123");

  @BeforeEach
  void setUpRestClientChain() {
    uriSpec = mock(RestClient.RequestBodyUriSpec.class);
    bodySpec = mock(RestClient.RequestBodySpec.class);
    responseSpec = mock(RestClient.ResponseSpec.class);

    lenient().when(resendRestClient.post()).thenReturn(uriSpec);
    lenient().when(uriSpec.uri("/emails")).thenReturn(bodySpec);
    lenient().when(bodySpec.body(any(Map.class))).thenReturn(bodySpec);
    lenient().when(bodySpec.retrieve()).thenReturn(responseSpec);

    lenient().when(resendProperties.from()).thenReturn("Atelier <onboarding@resend.dev>");
    lenient().when(applicationProperties.verificationSubject()).thenReturn("Confirme seu e-mail");
    lenient()
        .when(applicationProperties.verificationBaseUrl())
        .thenReturn("http://localhost:3000/auth/verify");
  }

  @Test
  @DisplayName("Deve enviar email com payload completo e URL de verificação correta")
  void shouldSendEmailWithCorrectPayload() {
    // Arrange
    when(responseSpec.toBodilessEntity()).thenReturn(ResponseEntity.ok().build());

    // Act
    sender.sendVerificationEmail(event);

    // Assert
    @SuppressWarnings("unchecked")
    ArgumentCaptor<Map<String, String>> bodyCaptor = ArgumentCaptor.forClass(Map.class);
    verify(bodySpec).body(bodyCaptor.capture());

    Map<String, String> body = bodyCaptor.getValue();
    assertThat(body)
        .containsEntry("from", "Atelier <onboarding@resend.dev>")
        .containsEntry("to", "ronaldinho@email.com")
        .containsEntry("subject", "Confirme seu e-mail");
    assertThat(body.get("html")).contains("http://localhost:3000/auth/verify?token=tk-123");
  }

  @Test
  @DisplayName("Deve URL-encodar tokens com caracteres especiais")
  void shouldUrlEncodeTokenWithSpecialChars() {
    // Arrange
    when(responseSpec.toBodilessEntity()).thenReturn(ResponseEntity.ok().build());
    UserRegisteredEvent eventWithSpecial =
        new UserRegisteredEvent("user@email.com", "abc/123+xy z");

    // Act
    sender.sendVerificationEmail(eventWithSpecial);

    // Assert
    @SuppressWarnings("unchecked")
    ArgumentCaptor<Map<String, String>> bodyCaptor = ArgumentCaptor.forClass(Map.class);
    verify(bodySpec).body(bodyCaptor.capture());

    String html = bodyCaptor.getValue().get("html");
    assertThat(html).contains("token=abc%2F123%2Bxy+z");
  }

  @Test
  @DisplayName("Deve capturar 4xx (HttpClientErrorException) e não relançar — não dispara retry")
  void shouldSwallow4xxErrors() {
    // Arrange
    HttpClientErrorException error =
        HttpClientErrorException.create(HttpStatus.BAD_REQUEST, "Bad Request", null, null, null);
    when(responseSpec.toBodilessEntity()).thenThrow(error);

    // Act & Assert
    assertThatNoException().isThrownBy(() -> sender.sendVerificationEmail(event));
  }

  @Test
  @DisplayName("Deve propagar 5xx (HttpServerErrorException) para o @Retryable")
  void shouldPropagate5xxErrors() {
    // Arrange
    HttpServerErrorException error =
        HttpServerErrorException.create(HttpStatus.INTERNAL_SERVER_ERROR, "Down", null, null, null);
    when(responseSpec.toBodilessEntity()).thenThrow(error);

    // Act & Assert
    assertThatThrownBy(() -> sender.sendVerificationEmail(event))
        .isInstanceOf(HttpServerErrorException.class);
  }

  @Test
  @DisplayName("Deve propagar ResourceAccessException (network) para o @Retryable")
  void shouldPropagateNetworkErrors() {
    // Arrange
    ResourceAccessException error = new ResourceAccessException("connection refused");
    when(responseSpec.toBodilessEntity()).thenThrow(error);

    // Act & Assert
    assertThatThrownBy(() -> sender.sendVerificationEmail(event))
        .isInstanceOf(ResourceAccessException.class);
  }

  @Test
  @DisplayName("@Recover deve logar e não relançar quando retentativas esgotam")
  void recoverShouldNotRethrow() {
    // Arrange
    ResourceAccessException error = new ResourceAccessException("retries exhausted");

    // Act & Assert
    assertThatNoException().isThrownBy(() -> sender.recoverSendVerificationEmail(error, event));
  }
}
