package com.tsm.atelier.domain.product.dto.v1.request;

import com.tsm.atelier.domain.product.ProductSize;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProductVariantRequestDTO(
    @NotNull ProductSize productSize, @NotNull @Min(0) Integer stock) {}
