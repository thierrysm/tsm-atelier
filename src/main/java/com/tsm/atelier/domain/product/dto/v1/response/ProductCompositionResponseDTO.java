package com.tsm.atelier.domain.product.dto.v1.response;

import com.tsm.atelier.domain.product.CompositionType;
import java.util.List;

public record ProductCompositionResponseDTO(
    Long id, CompositionType type, List<CompositionMaterialResponseDTO> materials) {}
