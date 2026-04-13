package com.tsm.atelier.domain.collection.dto.v1.response;

import com.tsm.atelier.domain.collection.CollectionStatus;
import java.time.Instant;

public record CollectionResponseDTO(
    Long id,
    String name,
    String slug,
    String description,
    String imageUrl,
    CollectionStatus status,
    Boolean featured,
    Boolean showInHeader,
    Boolean isNew,
    Integer displayOrder,
    Instant createdAt,
    Instant updatedAt) {}
