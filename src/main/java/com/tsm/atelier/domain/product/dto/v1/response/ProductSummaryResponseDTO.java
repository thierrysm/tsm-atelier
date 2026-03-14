package com.tsm.atelier.domain.product.dto.v1.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record ProductSummaryResponseDTO(
    UUID id,
    String name,
    BigDecimal price,
    String coverImageUrl,
    List<ColorSummaryResponseDTO> colors) {}
