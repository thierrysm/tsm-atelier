package com.tsm.atelier.domain.payment.controller.v1;

import com.tsm.atelier.domain.order.service.OrderService;
import com.tsm.atelier.domain.payment.PaymentEvent;
import com.tsm.atelier.domain.payment.PaymentGateway;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/webhooks")
@RequiredArgsConstructor
@Slf4j
public class PaymentWebhookController {

  private final PaymentGateway paymentGateway;
  private final OrderService orderService;

  @PostMapping("/stripe")
  public ResponseEntity<Void> handleStripeWebhook(
      @RequestBody String payload, @RequestHeader("Stripe-Signature") String signatureHeader) {

    try {
      Optional<PaymentEvent> eventOpt = paymentGateway.parseWebhookEvent(payload, signatureHeader);

      if (eventOpt.isEmpty()) {
        return ResponseEntity.ok().build();
      }

      PaymentEvent event = eventOpt.get();

      if (event instanceof PaymentEvent.Paid paidEvent) {
        log.info("Processando pagamento aprovado para o pedido: {}", paidEvent.orderId());
        orderService.handlePaymentPaid(paidEvent.orderId());
      } else if (event instanceof PaymentEvent.Expired expiredEvent) {
        log.info("Processando pagamento expirado para o pedido: {}", expiredEvent.orderId());
        orderService.handlePaymentExpired(expiredEvent.orderId());
      }

      return ResponseEntity.ok().build();
    } catch (IllegalArgumentException e) {
      log.warn("Assinatura de webhook inválida: {}", e.getMessage());
      return ResponseEntity.badRequest().build();
    } catch (Exception e) {
      log.error("Erro ao processar webhook: {}", e.getMessage(), e);
      return ResponseEntity.internalServerError().build();
    }
  }
}
