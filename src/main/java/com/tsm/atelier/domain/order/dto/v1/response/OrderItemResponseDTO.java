package com.tsm.atelier.domain.order.dto.v1.response;

import java.math.BigDecimal;

public record OrderItemResponseDTO(
    Long id,
    Long productVariantId,
    String productName,
    String colorName,
    String sizeName,
    String imageUrl,
    BigDecimal unitPrice,
    Integer quantity,
    BigDecimal subtotal) {}
