package com.tsm.atelier.domain.order.dto.v1.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CheckoutItemRequestDTO(
    @NotNull Long productVariantId, @NotNull @Min(1) Integer quantity) {}
