package com.tsm.atelier.domain.product.dto.v1.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Optional;

public record ProductColorPatchDTO(
    Optional<@Size(min = 1, max = 50) String> name,
    Optional<@Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$") String> hexCode) {}
