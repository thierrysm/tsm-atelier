package com.tsm.atelier.domain.order.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tsm.atelier.domain.auth.User;
import com.tsm.atelier.domain.order.Order;
import com.tsm.atelier.domain.order.OrderRepository;
import com.tsm.atelier.domain.order.OrderStatus;
import com.tsm.atelier.domain.order.dto.v1.request.CheckoutRequestDTO;
import com.tsm.atelier.domain.order.dto.v1.response.CheckoutResponseDTO;
import com.tsm.atelier.domain.payment.PaymentGateway;
import com.tsm.atelier.domain.product.ProductColor;
import com.tsm.atelier.domain.product.ProductStatus;
import com.tsm.atelier.domain.product.ProductVariant;
import com.tsm.atelier.domain.product.repository.ProductVariantRepository;
import com.tsm.atelier.exception.BusinessException;
import com.tsm.atelier.exception.EntityNotFoundException;
import com.tsm.atelier.factory.AuthTestFactory;
import com.tsm.atelier.factory.OrderTestFactory;
import com.tsm.atelier.factory.ProductColorTestFactory;
import com.tsm.atelier.factory.ProductTestFactory;
import com.tsm.atelier.factory.ProductVariantTestFactory;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("OrderService")
class OrderServiceTest {

  @Mock private OrderRepository orderRepository;
  @Mock private ProductVariantRepository productVariantRepository;
  @Mock private PaymentGateway paymentGateway;

  @InjectMocks private OrderService orderService;

  @Nested
  @DisplayName("Checkout")
  class Checkout {

    @Test
    @DisplayName("Deve realizar checkout com sucesso")
    void shouldCheckoutSuccessfully() {
      // Arrange
      User user = AuthTestFactory.aUser().build();
      CheckoutRequestDTO request = OrderTestFactory.aCheckoutRequest();

      ProductColor color =
          ProductColorTestFactory.aColor()
              .withProduct(ProductTestFactory.aProduct().withStatus(ProductStatus.ACTIVE).build())
              .build();

      ProductVariant variant =
          ProductVariantTestFactory.aVariant().withStock(10).withProductColor(color).build();
      variant.setId(1L);

      when(productVariantRepository.findLockedWithDetailsByIdIn(Set.of(1L)))
          .thenReturn(List.of(variant));
      when(orderRepository.saveAndFlush(any(Order.class))).thenAnswer(i -> i.getArgument(0));
      when(paymentGateway.generatePaymentUrl(any(Order.class), any(String.class)))
          .thenReturn("http://stripe.url");

      // Act
      CheckoutResponseDTO response = orderService.checkout(user, request);

      // Assert
      assertThat(response.redirectUrl()).isEqualTo("http://stripe.url");
      assertThat(variant.getStock()).isEqualTo(9);
      verify(orderRepository).saveAndFlush(any(Order.class));
    }

    @Test
    @DisplayName("Deve falhar se estoque for insuficiente")
    void shouldFailWhenStockIsInsufficient() {
      // Arrange
      User user = AuthTestFactory.aUser().build();
      CheckoutRequestDTO request = OrderTestFactory.aCheckoutRequest();

      ProductColor color =
          ProductColorTestFactory.aColor()
              .withProduct(ProductTestFactory.aProduct().withStatus(ProductStatus.ACTIVE).build())
              .build();

      ProductVariant variant =
          ProductVariantTestFactory.aVariant().withStock(0).withProductColor(color).build();
      variant.setId(1L);

      when(productVariantRepository.findLockedWithDetailsByIdIn(Set.of(1L)))
          .thenReturn(List.of(variant));

      // Act & Assert
      assertThatThrownBy(() -> orderService.checkout(user, request))
          .isInstanceOf(BusinessException.class)
          .hasMessageContaining("Estoque insuficiente");
    }
  }

  @Nested
  @DisplayName("Confirmação de Pagamento")
  class PaymentConfirmation {

    @Test
    @DisplayName("Deve atualizar status para PAID ao receber confirmação")
    void shouldUpdateStatusToPaid() {
      // Arrange
      Order order = OrderTestFactory.aOrder();
      UUID orderId = order.getId();
      when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

      // Act
      orderService.handlePaymentPaid(orderId);

      // Assert
      assertThat(order.getStatus()).isEqualTo(OrderStatus.PAID);
      verify(orderRepository).save(order);
    }

    @Test
    @DisplayName("Deve falhar se pedido não for encontrado")
    void shouldFailWhenOrderNotFound() {
      UUID orderId = UUID.randomUUID();
      when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

      assertThatThrownBy(() -> orderService.handlePaymentPaid(orderId))
          .isInstanceOf(EntityNotFoundException.class);
    }
  }

  @Nested
  @DisplayName("Pagamento Expirado")
  class PaymentExpired {

    @Test
    @DisplayName("Deve cancelar pedido e restaurar estoque ao expirar")
    void shouldCancelAndRestoreStock() {
      // Arrange
      Order order = OrderTestFactory.aOrder();
      UUID orderId = order.getId();
      ProductVariant variant = ProductVariantTestFactory.aVariant().withStock(5).build();

      when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
      when(productVariantRepository.findById(1L)).thenReturn(Optional.of(variant));

      // Act
      orderService.handlePaymentExpired(orderId);

      // Assert
      assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCELLED);
      assertThat(variant.getStock()).isEqualTo(6); // 5 + 1 da order
      verify(orderRepository).save(order);
      verify(productVariantRepository).save(variant);
    }
  }
}
