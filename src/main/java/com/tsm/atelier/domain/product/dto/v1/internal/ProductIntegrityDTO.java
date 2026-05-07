package com.tsm.atelier.domain.product.dto.v1.internal;

public record ProductIntegrityDTO(
    long productId,
    long colorCount,
    long variantCount,
    long imageCount,
    long colorsWithCoverImage) {}
