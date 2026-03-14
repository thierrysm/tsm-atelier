package com.tsm.atelier.domain.product.dto.v1.response;

import java.util.UUID;

public record ProductCareResponseDTO(
    UUID id, Boolean allowed, String instruction, Integer displayOrder) {}
