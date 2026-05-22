package com.tsm.atelier.domain.product.dto.v1.response;

import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;

@Builder
public record ProductSummaryResponseDTO(
    Long id,
    String name,
    BigDecimal price,
    BigDecimal promotionalPrice,
    String coverImageUrl,
    List<ColorSummaryResponseDTO> colors) {}
