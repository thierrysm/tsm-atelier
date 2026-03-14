package com.tsm.atelier.domain.product.dto.v1.response;

import com.tsm.atelier.domain.product.ProductSize;
import java.util.UUID;

public record ProductVariantResponseDTO(UUID id, ProductSize size, Integer stock) {}
