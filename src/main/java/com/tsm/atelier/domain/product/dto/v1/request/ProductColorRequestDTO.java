package com.tsm.atelier.domain.product.dto.v1.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ProductColorRequestDTO(
    @NotBlank @Size(max = 50) String name,
    @NotBlank
        @Pattern(
            regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$",
            message = "Deve ser um código de cor hexadecimal válido")
        String hexCode) {}
