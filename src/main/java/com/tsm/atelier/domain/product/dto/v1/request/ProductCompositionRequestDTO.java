package com.tsm.atelier.domain.product.dto.v1.request;

import com.tsm.atelier.domain.product.CompositionType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record ProductCompositionRequestDTO(
    @NotNull CompositionType type, @NotNull @Valid List<CompositionMaterialRequestDTO> materials) {}
