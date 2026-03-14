package com.tsm.atelier.domain.product.dto.v1.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record ProductRequestDTO(
    @NotBlank @Size(max = 150) String name,
    @NotBlank String description,
    @NotNull @Positive BigDecimal price,
    @NotBlank String material,
    UUID collectionId,
    @NotEmpty @Valid List<ProductCareRequestDTO> careInstructions,
    @NotEmpty @Valid List<ProductColorRequestDTO> colors) {}
