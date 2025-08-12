package com.tm.tsmatelier.core.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "product_images")
class ProductImageEntity(
    @Column(nullable = false)
    var imageUrl: String,
    @Column(name = "alt_text")
    var altText: String?,
    @Column(nullable = false)
    var isPrimary: Boolean = false,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    var product: ProductEntity,
) : AuditableEntity()
