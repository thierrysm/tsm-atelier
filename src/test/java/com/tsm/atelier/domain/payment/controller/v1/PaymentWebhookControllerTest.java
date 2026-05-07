package com.tsm.atelier.domain.payment.controller.v1;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tsm.atelier.domain.order.service.OrderService;
import com.tsm.atelier.domain.payment.PaymentEvent;
import com.tsm.atelier.domain.payment.PaymentGateway;
import com.tsm.atelier.exception.handler.GlobalExceptionHandler;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@DisplayName("PaymentWebhookController")
class PaymentWebhookControllerTest {

  private MockMvc mockMvc;

  @Mock private PaymentGateway paymentGateway;
  @Mock private OrderService orderService;

  @InjectMocks private PaymentWebhookController controller;

  @BeforeEach
  void setUp() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(controller)
            .setControllerAdvice(new GlobalExceptionHandler())
            .build();
  }

  @Test
  @DisplayName("Deve processar webhook de pagamento aprovado")
  void shouldProcessPaidWebhook() throws Exception {
    // Arrange
    UUID orderId = UUID.randomUUID();
    PaymentEvent event = new PaymentEvent.Paid("evt_123", orderId, "pi_123");

    when(paymentGateway.parseWebhookEvent(anyString(), anyString())).thenReturn(Optional.of(event));

    // Act & Assert
    mockMvc
        .perform(
            post("/api/v1/webhooks/stripe")
                .header("Stripe-Signature", "sig_123")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    verify(orderService).handlePaymentPaid(orderId);
  }

  @Test
  @DisplayName("Deve processar webhook de pagamento expirado")
  void shouldProcessExpiredWebhook() throws Exception {
    // Arrange
    UUID orderId = UUID.randomUUID();
    PaymentEvent event = new PaymentEvent.Expired("evt_123", orderId);

    when(paymentGateway.parseWebhookEvent(anyString(), anyString())).thenReturn(Optional.of(event));

    // Act & Assert
    mockMvc
        .perform(
            post("/api/v1/webhooks/stripe")
                .header("Stripe-Signature", "sig_123")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    verify(orderService).handlePaymentExpired(orderId);
  }

  @Test
  @DisplayName("Deve retornar 400 se assinatura for inválida")
  void shouldReturnBadRequestOnInvalidSignature() throws Exception {
    // Arrange
    when(paymentGateway.parseWebhookEvent(anyString(), anyString()))
        .thenThrow(new IllegalArgumentException("Invalid signature"));

    // Act & Assert
    mockMvc
        .perform(
            post("/api/v1/webhooks/stripe")
                .header("Stripe-Signature", "invalid")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }
}
