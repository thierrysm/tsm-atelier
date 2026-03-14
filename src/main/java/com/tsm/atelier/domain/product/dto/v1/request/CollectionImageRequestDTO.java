package com.tsm.atelier.domain.product.dto.v1.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CollectionImageRequestDTO(@NotBlank String url, @Size(max = 100) String fileName) {}
