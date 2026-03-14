package com.tsm.atelier.domain.product.dto.v1.response;

import com.tsm.atelier.domain.product.ProductSize;

public record ProductVariantResponseDTO(Long id, ProductSize size, Integer stock) {}
