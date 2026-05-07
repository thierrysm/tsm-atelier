package com.tsm.atelier.domain.listener;

import com.tsm.atelier.domain.common.EmailSender;
import com.tsm.atelier.domain.common.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class UserRegistrationListener {

  private final EmailSender emailSender;

  @Async
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleUserRegisteredEvent(UserRegisteredEvent event) {
    emailSender.sendVerificationEmail(event);
  }
}
