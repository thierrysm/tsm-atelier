package com.tsm.atelier.domain.product.dto.v1.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Optional;

public record ProductPatchDTO(
    Optional<@Size(min = 3, max = 150) String> name,
    Optional<@Size(min = 5) String> description,
    Optional<@DecimalMin("0.01") BigDecimal> price,
    Optional<@Size(min = 3, max = 100) String> material) {}
