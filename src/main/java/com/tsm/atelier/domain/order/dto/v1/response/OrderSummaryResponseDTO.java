package com.tsm.atelier.domain.order.dto.v1.response;

import com.tsm.atelier.domain.order.OrderStatus;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record OrderSummaryResponseDTO(
    UUID id, OrderStatus status, BigDecimal totalAmount, Instant createdAt, int itemsCount) {}
