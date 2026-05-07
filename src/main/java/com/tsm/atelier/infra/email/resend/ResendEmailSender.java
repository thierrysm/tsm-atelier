package com.tsm.atelier.infra.email.resend;

import com.tsm.atelier.config.ApplicationProperties;
import com.tsm.atelier.domain.common.EmailSender;
import com.tsm.atelier.domain.common.UserRegisteredEvent;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
@RequiredArgsConstructor
public class ResendEmailSender implements EmailSender {

  private static final Logger log = LoggerFactory.getLogger(ResendEmailSender.class);

  private final RestClient resendRestClient;
  private final ResendProperties resendProperties;
  private final ApplicationProperties applicationProperties;

  @Override
  @Retryable(
      retryFor = {HttpServerErrorException.class, ResourceAccessException.class},
      maxAttempts = 3,
      backoff = @Backoff(delay = 1000, multiplier = 2.0, maxDelay = 5000))
  public void sendVerificationEmail(UserRegisteredEvent event) {
    try {
      log.info("email.verification.send.start email={}", event.email());

      resendRestClient
          .post()
          .uri("/emails")
          .body(
              Map.of(
                  "from", resendProperties.from(),
                  "to", event.email(),
                  "subject", applicationProperties.verificationSubject(),
                  "html", buildHtml(event.token())))
          .retrieve()
          .toBodilessEntity();

      log.info("email.verification.send.success email={}", event.email());
    } catch (HttpClientErrorException ex) {
      log.error(
          "email.verification.send.client_error email={} status={} body={}",
          event.email(),
          ex.getStatusCode(),
          ex.getResponseBodyAsString(),
          ex);
    }
  }

  @Recover
  void recoverSendVerificationEmail(RestClientException ex, UserRegisteredEvent event) {
    log.error(
        "email.verification.send.exhausted email={} reason={}",
        event.email(),
        ex.getClass().getSimpleName(),
        ex);
  }

  private String buildHtml(String token) {
    String encoded = URLEncoder.encode(token, StandardCharsets.UTF_8);
    String url = applicationProperties.verificationBaseUrl() + "?token=" + encoded;
    return "<p>Clique no link abaixo para confirmar seu e-mail:</p>"
        + "<p><a href=\""
        + url
        + "\">Confirmar e-mail</a></p>";
  }
}
