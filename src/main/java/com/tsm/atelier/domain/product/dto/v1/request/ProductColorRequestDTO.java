package com.tsm.atelier.domain.product.dto.v1.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;

public record ProductColorRequestDTO(
    @NotBlank @Size(max = 50) String name,
    @Pattern(
            regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$",
            message = "Must be a valid hex color code")
        String hexCode,
    @NotEmpty @Valid List<ProductImageRequestDTO> images,
    @NotEmpty @Valid List<ProductVariantRequestDTO> variants) {}
