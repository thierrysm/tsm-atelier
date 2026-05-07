package com.tsm.atelier.domain.common;

public interface EmailSender {
  void sendVerificationEmail(UserRegisteredEvent event);
}
