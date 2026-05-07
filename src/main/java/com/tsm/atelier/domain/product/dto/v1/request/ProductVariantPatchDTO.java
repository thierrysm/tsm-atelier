package com.tsm.atelier.domain.product.dto.v1.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProductVariantPatchDTO(@NotNull @Min(0) Integer stock) {}
