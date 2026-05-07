package com.tsm.atelier.infra.payment.stripe;

import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.net.RequestOptions;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import com.tsm.atelier.domain.order.Order;
import com.tsm.atelier.domain.order.OrderItem;
import com.tsm.atelier.domain.payment.PaymentEvent;
import com.tsm.atelier.domain.payment.PaymentGateway;
import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StripePaymentGateway implements PaymentGateway {

  private static final String CURRENCY = "brl";

  private final StripeProperties properties;

  @PostConstruct
  void init() {
    Stripe.apiKey = properties.apiKey();
  }

  @Override
  public String generatePaymentUrl(Order order, String customerEmail) {
    SessionCreateParams.Builder builder =
        SessionCreateParams.builder()
            .setMode(SessionCreateParams.Mode.PAYMENT)
            .setSuccessUrl(properties.successUrl())
            .setCancelUrl(properties.cancelUrl())
            .setCustomerEmail(customerEmail)
            .setClientReferenceId(order.getId().toString())
            .putMetadata("orderId", order.getId().toString());

    for (OrderItem item : order.getItems()) {
      String itemName =
          item.getProductName() + " — " + item.getColorName() + " / " + item.getSizeName();

      SessionCreateParams.LineItem.PriceData.ProductData.Builder productData =
          SessionCreateParams.LineItem.PriceData.ProductData.builder().setName(itemName);

      if (item.getImageUrl() != null && item.getImageUrl().startsWith("http")) {
        productData.addImage(item.getImageUrl());
      }

      builder.addLineItem(
          SessionCreateParams.LineItem.builder()
              .setQuantity((long) item.getQuantity())
              .setPriceData(
                  SessionCreateParams.LineItem.PriceData.builder()
                      .setCurrency(CURRENCY)
                      .setUnitAmount(toMinorUnits(item.getUnitPrice()))
                      .setProductData(productData.build())
                      .build())
              .build());
    }

    RequestOptions options =
        RequestOptions.builder().setIdempotencyKey("checkout-" + order.getId()).build();

    try {
      Session session = Session.create(builder.build(), options);
      return session.getUrl();
    } catch (StripeException e) {
      throw new IllegalStateException(
          "Falha ao criar sessão de checkout no Stripe: " + e.getMessage(), e);
    }
  }

  @Override
  public Optional<PaymentEvent> parseWebhookEvent(String rawPayload, String signatureHeader) {
    Event event;
    try {
      event = Webhook.constructEvent(rawPayload, signatureHeader, properties.webhookSecret());
    } catch (SignatureVerificationException e) {
      throw new IllegalArgumentException("Assinatura do webhook Stripe inválida", e);
    }

    StripeObject object = event.getDataObjectDeserializer().getObject().orElse(null);
    if (!(object instanceof Session session)) {
      return Optional.empty();
    }
    UUID orderId = parseOrderId(session.getClientReferenceId());
    if (orderId == null) {
      return Optional.empty();
    }

    return switch (event.getType()) {
      case "checkout.session.completed" -> {
        if ("paid".equals(session.getPaymentStatus())) {
          yield Optional.of(
              new PaymentEvent.Paid(event.getId(), orderId, session.getPaymentIntent()));
        }
        yield Optional.empty();
      }
      case "checkout.session.async_payment_succeeded" ->
          Optional.of(new PaymentEvent.Paid(event.getId(), orderId, session.getPaymentIntent()));
      case "checkout.session.expired", "checkout.session.async_payment_failed" ->
          Optional.of(new PaymentEvent.Expired(event.getId(), orderId));
      default -> Optional.empty();
    };
  }

  private UUID parseOrderId(String value) {
    if (value == null || value.isBlank()) {
      return null;
    }
    try {
      return UUID.fromString(value);
    } catch (IllegalArgumentException e) {
      return null;
    }
  }

  private long toMinorUnits(BigDecimal value) {
    return value.movePointRight(2).longValueExact();
  }
}
