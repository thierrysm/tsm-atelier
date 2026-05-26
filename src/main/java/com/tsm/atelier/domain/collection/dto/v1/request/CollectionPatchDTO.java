package com.tsm.atelier.domain.collection.dto.v1.request;

import com.tsm.atelier.domain.product.TargetAudience;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.util.Optional;

public record CollectionPatchDTO(
    Optional<@Size(min = 3, max = 100) String> name,
    Optional<@Size(min = 5) String> description,
    Optional<TargetAudience> targetAudience,
    Optional<Boolean> featured,
    Optional<Boolean> showInHeader,
    Optional<Boolean> isNew,
    Optional<@Min(1) Integer> displayOrder) {}
