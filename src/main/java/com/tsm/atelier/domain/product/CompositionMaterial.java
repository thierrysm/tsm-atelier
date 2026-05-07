package com.tsm.atelier.domain.product;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record CompositionMaterial(
    @Column(nullable = false, length = 50) String name,
    @Column(nullable = false) Integer percentage) {}
