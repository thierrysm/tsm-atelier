package com.tsm.atelier.domain.product.dto.v1.internal;

public record ProductIntegrityDTO(
    Long productId,
    long colorCount,
    long variantCount,
    long imageCount,
    long coverImageCount,
    Long colorsWithCoverImage,
    long careInstructionsCount,
    long compositionCount) {}
