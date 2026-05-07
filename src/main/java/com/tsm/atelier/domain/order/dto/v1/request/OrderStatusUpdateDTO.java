package com.tsm.atelier.domain.order.dto.v1.request;

import com.tsm.atelier.domain.order.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record OrderStatusUpdateDTO(@NotNull OrderStatus status) {}
