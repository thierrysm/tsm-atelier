package com.tsm.atelier.domain.auth.service;

import com.tsm.atelier.config.ApplicationProperties;
import com.tsm.atelier.domain.auth.EmailVerificationToken;
import com.tsm.atelier.domain.auth.User;
import com.tsm.atelier.domain.auth.UserStatus;
import com.tsm.atelier.domain.auth.repository.EmailVerificationTokenRepository;
import com.tsm.atelier.domain.auth.repository.UserRepository;
import com.tsm.atelier.domain.common.UserRegisteredEvent;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserVerificationService {
  private static final Logger log = LoggerFactory.getLogger(UserVerificationService.class);

  private final UserRepository userRepository;
  private final EmailVerificationTokenRepository emailVerificationTokenRepository;
  private final ApplicationEventPublisher applicationEventPublisher;
  private final ApplicationProperties applicationProperties;

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void resendVerification(String email) {
    userRepository
        .findByEmail(email)
        .ifPresent(
            user -> {
              if (user.getStatus() == UserStatus.ACTIVE
                  || Boolean.TRUE.equals(user.getEmailVerified())) {
                log.info(
                    "resend-verification ignorado: email já verificado userId={}", user.getId());
                return;
              }

              emailVerificationTokenRepository.invalidateActiveByUserId(user.getId());
              String token = createVerificationToken(user);
              applicationEventPublisher.publishEvent(new UserRegisteredEvent(email, token));
            });
  }

  public String createVerificationToken(User user) {
    String token = UUID.randomUUID().toString();
    Instant expiresAt =
        Instant.now().plusSeconds(applicationProperties.emailVerificationExpiration());

    EmailVerificationToken verificationToken = new EmailVerificationToken();
    verificationToken.setToken(token);
    verificationToken.setUser(user);
    verificationToken.setExpiresAt(expiresAt);
    verificationToken.setUsed(false);

    emailVerificationTokenRepository.save(verificationToken);
    return token;
  }
}
