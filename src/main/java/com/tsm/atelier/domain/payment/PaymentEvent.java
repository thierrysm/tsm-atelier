package com.tsm.atelier.domain.payment;

import java.util.UUID;

public sealed interface PaymentEvent {

  String externalEventId();

  UUID orderId();

  record Paid(String externalEventId, UUID orderId, String externalPaymentId)
      implements PaymentEvent {}

  record Expired(String externalEventId, UUID orderId) implements PaymentEvent {}
}
