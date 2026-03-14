package com.tsm.atelier.domain.product.dto.v1.response;

import java.util.UUID;

public record ProductImageResponseDTO(
    UUID id,
    String url,
    String fileName,
    String contentType,
    Long fileSize,
    Integer displayOrder,
    Boolean isCover) {}
