package com.tsm.atelier.domain.collection.dto.v1.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CollectionRequestDTO(
    @NotBlank @Size(min = 3, max = 100) String name,
    @NotBlank @Size(min = 5) String description,
    @NotNull Boolean featured,
    @NotNull Boolean showInHeader,
    @NotNull Boolean isNew,
    @NotNull @Min(1) Integer displayOrder) {}
