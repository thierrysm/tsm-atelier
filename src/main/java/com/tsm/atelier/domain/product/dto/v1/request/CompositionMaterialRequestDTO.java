package com.tsm.atelier.domain.product.dto.v1.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CompositionMaterialRequestDTO(
    @NotBlank @Size(min = 3, max = 50) String name,
    @NotNull @Min(1) @Max(100) Integer percentage) {}
