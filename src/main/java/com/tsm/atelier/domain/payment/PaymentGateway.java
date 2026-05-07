package com.tsm.atelier.domain.payment;

import com.tsm.atelier.domain.order.Order;
import java.util.Optional;

public interface PaymentGateway {

  String generatePaymentUrl(Order order, String customerEmail);

  Optional<PaymentEvent> parseWebhookEvent(String rawPayload, String signatureHeader);
}
