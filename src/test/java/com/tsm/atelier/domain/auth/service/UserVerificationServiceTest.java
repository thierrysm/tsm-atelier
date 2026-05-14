package com.tsm.atelier.domain.auth.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tsm.atelier.config.ApplicationProperties;
import com.tsm.atelier.domain.auth.EmailVerificationToken;
import com.tsm.atelier.domain.auth.User;
import com.tsm.atelier.domain.auth.UserStatus;
import com.tsm.atelier.domain.auth.repository.EmailVerificationTokenRepository;
import com.tsm.atelier.domain.auth.repository.UserRepository;
import com.tsm.atelier.domain.common.UserRegisteredEvent;
import com.tsm.atelier.factory.AuthTestFactory;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserVerificationService")
class UserVerificationServiceTest {

  @Mock private UserRepository userRepository;
  @Mock private EmailVerificationTokenRepository emailVerificationTokenRepository;
  @Mock private ApplicationEventPublisher eventPublisher;
  @Mock private ApplicationProperties applicationProperties;

  @InjectMocks private UserVerificationService userVerificationService;

  @Nested
  @DisplayName("Reenviar verificação")
  class ResendVerification {

    @Test
    @DisplayName(
        "Deve invalidar tokens antigos, criar novo e publicar evento para usuário INACTIVE")
    void shouldInvalidateOldTokensAndPublishEventForInactiveUser() {
      // Arrange
      User user =
          AuthTestFactory.aUser().withStatus(UserStatus.INACTIVE).withEmailVerified(false).build();
      when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
      when(applicationProperties.emailVerificationExpiration()).thenReturn(14400L);

      // Act
      userVerificationService.resendVerification(user.getEmail());

      // Assert
      verify(emailVerificationTokenRepository).invalidateActiveByUserId(user.getId());
      verify(emailVerificationTokenRepository).save(any(EmailVerificationToken.class));
      verify(eventPublisher)
          .publishEvent(argThat((UserRegisteredEvent ev) -> ev.email().equals(user.getEmail())));
    }

    @Test
    @DisplayName("Não deve fazer nada quando usuário não é encontrado")
    void shouldDoNothingWhenUserNotFound() {
      // Arrange
      when(userRepository.findByEmail("nao-existe@email.com")).thenReturn(Optional.empty());

      // Act
      userVerificationService.resendVerification("nao-existe@email.com");

      // Assert
      verify(emailVerificationTokenRepository, never()).invalidateActiveByUserId(any());
      verify(emailVerificationTokenRepository, never()).save(any());
      verify(eventPublisher, never()).publishEvent(any());
    }

    @Test
    @DisplayName("Não deve reenviar quando usuário já está ACTIVE")
    void shouldNotResendWhenUserIsActive() {
      // Arrange
      User user = AuthTestFactory.aUser().withStatus(UserStatus.ACTIVE).build();
      when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

      // Act
      userVerificationService.resendVerification(user.getEmail());

      // Assert
      verify(emailVerificationTokenRepository, never()).invalidateActiveByUserId(any());
      verify(eventPublisher, never()).publishEvent(any());
    }

    @Test
    @DisplayName("Não deve reenviar quando emailVerified=true")
    void shouldNotResendWhenEmailAlreadyVerified() {
      // Arrange
      User user =
          AuthTestFactory.aUser().withStatus(UserStatus.INACTIVE).withEmailVerified(true).build();
      when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

      // Act
      userVerificationService.resendVerification(user.getEmail());

      // Assert
      verify(emailVerificationTokenRepository, never()).invalidateActiveByUserId(any());
      verify(eventPublisher, never()).publishEvent(any());
    }
  }

  @Nested
  @DisplayName("Criar token de verificação")
  class CreateVerificationToken {

    @Test
    @DisplayName("Deve criar e salvar um novo token")
    void shouldCreateAndSaveNewToken() {
      // Arrange
      User user = AuthTestFactory.aUser().build();
      when(applicationProperties.emailVerificationExpiration()).thenReturn(14400L);

      // Act
      String token = userVerificationService.createVerificationToken(user);

      // Assert
      assertThat(token).isNotBlank();
      verify(emailVerificationTokenRepository)
          .save(
              argThat(t -> t.getUser().equals(user) && t.getToken().equals(token) && !t.getUsed()));
    }
  }
}
