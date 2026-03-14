package com.tsm.atelier.domain.product.dto.v1.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record ProductDetailsResponseDTO(
    UUID id,
    String name,
    String description,
    BigDecimal price,
    String material,
    String collectionName,
    List<ProductCareResponseDTO> careInstructions,
    List<ColorSummaryResponseDTO> colors) {}
