package com.tsm.atelier.domain.product.dto.v1.response;

import java.util.List;

public record ProductColorDetailsResponseDTO(
    Long id,
    String name,
    String hexCode,
    List<ProductImageResponseDTO> images,
    List<ProductVariantResponseDTO> variants) {}
