package com.tsm.atelier.domain.order.service;

import com.tsm.atelier.domain.auth.User;
import com.tsm.atelier.domain.order.Order;
import com.tsm.atelier.domain.order.OrderItem;
import com.tsm.atelier.domain.order.OrderRepository;
import com.tsm.atelier.domain.order.OrderStatus;
import com.tsm.atelier.domain.order.ShippingAddress;
import com.tsm.atelier.domain.order.dto.v1.request.CheckoutItemRequestDTO;
import com.tsm.atelier.domain.order.dto.v1.request.CheckoutRequestDTO;
import com.tsm.atelier.domain.order.dto.v1.request.OrderStatusUpdateDTO;
import com.tsm.atelier.domain.order.dto.v1.request.ShippingAddressRequestDTO;
import com.tsm.atelier.domain.order.dto.v1.response.CheckoutResponseDTO;
import com.tsm.atelier.domain.order.dto.v1.response.OrderDetailsResponseDTO;
import com.tsm.atelier.domain.order.dto.v1.response.OrderSummaryResponseDTO;
import com.tsm.atelier.domain.order.mapper.OrderMapper;
import com.tsm.atelier.domain.payment.PaymentGateway;
import com.tsm.atelier.domain.product.ProductColor;
import com.tsm.atelier.domain.product.ProductImage;
import com.tsm.atelier.domain.product.ProductStatus;
import com.tsm.atelier.domain.product.ProductVariant;
import com.tsm.atelier.domain.product.repository.ProductVariantRepository;
import com.tsm.atelier.exception.BusinessException;
import com.tsm.atelier.exception.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final ProductVariantRepository productVariantRepository;
  private final PaymentGateway paymentGateway;
  private final OrderMapper orderMapper;

  @Transactional(readOnly = true)
  public Page<OrderSummaryResponseDTO> getMyOrders(User user, Pageable pageable) {
    return orderRepository
        .findAllByUserIdOrderByCreatedAtDesc(user.getId(), pageable)
        .map(orderMapper::toSummaryResponse);
  }

  @Transactional(readOnly = true)
  public OrderDetailsResponseDTO getMyOrderDetails(User user, UUID orderId) {
    Order order =
        orderRepository
            .findByIdAndUserId(orderId, user.getId())
            .orElseThrow(() -> new EntityNotFoundException("Order", "id", orderId));

    return orderMapper.toDetailsResponse(order);
  }

  @Transactional(readOnly = true)
  public Page<OrderSummaryResponseDTO> getAllOrders(Pageable pageable) {
    return orderRepository.findAll(pageable).map(orderMapper::toSummaryResponse);
  }

  @Transactional(readOnly = true)
  public OrderDetailsResponseDTO getOrderDetailsForAdmin(UUID orderId) {
    Order order =
        orderRepository
            .findById(orderId)
            .orElseThrow(() -> new EntityNotFoundException("Order", "id", orderId));

    return orderMapper.toDetailsResponse(order);
  }

  @Transactional
  public OrderDetailsResponseDTO updateOrderStatus(UUID orderId, OrderStatusUpdateDTO request) {
    Order order =
        orderRepository
            .findById(orderId)
            .orElseThrow(() -> new EntityNotFoundException("Order", "id", orderId));

    if (order.getStatus() == OrderStatus.PENDING_PAYMENT
        && (request.status() == OrderStatus.SHIPPED || request.status() == OrderStatus.DELIVERED)) {
      throw new BusinessException(
          "Não é possível alterar para " + request.status() + " pois o pedido não está PAGO.");
    }

    if (order.getStatus() == OrderStatus.CANCELLED) {
      throw new BusinessException("Não é possível alterar o status de um pedido cancelado.");
    }

    order.setStatus(request.status());
    return orderMapper.toDetailsResponse(orderRepository.save(order));
  }

  @Transactional
  public CheckoutResponseDTO checkout(User user, CheckoutRequestDTO request) {
    Map<Long, Integer> requestedQty = collectQuantities(request.items());

    List<ProductVariant> variants =
        productVariantRepository.findLockedWithDetailsByIdIn(requestedQty.keySet());

    if (variants.size() != requestedQty.size()) {
      throw new BusinessException("Uma ou mais variantes do pedido não foram encontradas.");
    }

    List<OrderItem> items = new ArrayList<>();
    BigDecimal total = BigDecimal.ZERO;

    for (ProductVariant variant : variants) {
      ProductColor color = variant.getProductColor();
      var product = color.getProduct();

      if (product.getStatus() != ProductStatus.ACTIVE) {
        throw new BusinessException("Produto indisponível para compra: " + product.getName());
      }

      int qty = requestedQty.get(variant.getId());
      if (variant.getStock() < qty) {
        throw new BusinessException(
            "Estoque insuficiente para "
                + product.getName()
                + " ("
                + color.getName()
                + " / "
                + variant.getSize()
                + ")");
      }
      variant.setStock(variant.getStock() - qty);

      OrderItem item = new OrderItem();
      item.setProductVariantId(variant.getId());
      item.setProductName(product.getName());
      item.setColorName(color.getName());
      item.setSizeName(variant.getSize().name());
      item.setImageUrl(findCoverImageUrl(color));
      item.setUnitPrice(product.getPrice());
      item.setQuantity(qty);
      items.add(item);

      total = total.add(item.getSubtotal());
    }

    Order order = new Order();
    order.setUserId(user.getId());
    order.setStatus(OrderStatus.PENDING_PAYMENT);
    order.setTotalAmount(total);
    order.setShippingAddress(toShippingAddress(request.shippingAddress()));
    items.forEach(order::addItem);
    Order persisted = orderRepository.saveAndFlush(order);

    String paymentUrl = paymentGateway.generatePaymentUrl(persisted, user.getEmail());

    return new CheckoutResponseDTO(persisted.getId(), paymentUrl);
  }

  private Map<Long, Integer> collectQuantities(List<CheckoutItemRequestDTO> items) {
    Map<Long, Integer> map = new HashMap<>();
    for (CheckoutItemRequestDTO item : items) {
      Integer existing = map.put(item.productVariantId(), item.quantity());
      if (existing != null) {
        throw new BusinessException("Variante duplicada no pedido: " + item.productVariantId());
      }
    }
    return map;
  }

  private String findCoverImageUrl(ProductColor color) {
    return color.getImages().stream()
        .filter(img -> Boolean.TRUE.equals(img.getIsCover()))
        .map(ProductImage::getUrl)
        .findFirst()
        .orElse(null);
  }

  private ShippingAddress toShippingAddress(ShippingAddressRequestDTO dto) {
    return new ShippingAddress(
        dto.recipientName(),
        dto.cep(),
        dto.street(),
        dto.number(),
        dto.complement(),
        dto.neighborhood(),
        dto.city(),
        dto.state());
  }

  @Transactional
  public void handlePaymentPaid(UUID orderId) {
    Order order =
        orderRepository
            .findById(orderId)
            .orElseThrow(() -> new EntityNotFoundException("Order", "id", orderId));

    if (order.getStatus() != OrderStatus.PENDING_PAYMENT) {
      throw new BusinessException("Pedido já foi processado ou cancelado.");
    }

    order.setStatus(OrderStatus.PAID);
    orderRepository.save(order);
  }

  @Transactional
  public void handlePaymentExpired(UUID orderId) {
    Order order =
        orderRepository
            .findById(orderId)
            .orElseThrow(() -> new EntityNotFoundException("Order", "id", orderId));

    if (order.getStatus() != OrderStatus.PENDING_PAYMENT) {
      return;
    }

    order.setStatus(OrderStatus.CANCELLED);

    for (OrderItem item : order.getItems()) {
      ProductVariant variant =
          productVariantRepository
              .findById(item.getProductVariantId())
              .orElseThrow(
                  () ->
                      new BusinessException(
                          "Variante não encontrada ao restaurar estoque: "
                              + item.getProductVariantId()));

      variant.setStock(variant.getStock() + item.getQuantity());
      productVariantRepository.save(variant);
    }

    orderRepository.save(order);
  }
}
