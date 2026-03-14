package com.tsm.atelier.domain.product.dto.v1.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CollectionRequestDTO(
    @NotBlank @Size(max = 100) String name,
    @NotBlank String description,
    @NotNull Integer year,
    @Valid CollectionImageRequestDTO image) {}
