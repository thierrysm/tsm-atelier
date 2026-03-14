package com.tsm.atelier.domain.product.dto.v1.response;

public record ProductImageResponseDTO(
    Long id, String url, String fileName, Integer displayOrder, Boolean isCover) {}
