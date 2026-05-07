package com.tsm.atelier.domain.order.dto.v1.response;

import com.tsm.atelier.domain.order.OrderStatus;
import com.tsm.atelier.domain.order.ShippingAddress;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record OrderDetailsResponseDTO(
    UUID id,
    UUID userId,
    OrderStatus status,
    BigDecimal totalAmount,
    ShippingAddress shippingAddress,
    List<OrderItemResponseDTO> items,
    Instant createdAt,
    Instant updatedAt) {}
