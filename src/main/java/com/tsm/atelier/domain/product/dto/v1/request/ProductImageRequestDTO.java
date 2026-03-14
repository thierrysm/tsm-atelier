package com.tsm.atelier.domain.product.dto.v1.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductImageRequestDTO(
    @NotBlank String url,
    @NotBlank @Size(max = 100) String fileName,
    @NotNull Integer displayOrder,
    @NotNull Boolean isCover) {}
