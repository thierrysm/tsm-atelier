package com.tm.tsmatelier.core.entity

import com.tm.tsmatelier.core.entity.enums.ProductSizeEnum
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "product_variants")
class ProductVariantEntity(
    @Column(nullable = false, unique = true, length = 30)
    var sku: String,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 2)
    var size: ProductSizeEnum,
    @Column(nullable = false, length = 40)
    var color: String,
    @Column(nullable = false)
    var quantityInStock: Int,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    var product: ProductEntity,
) : AuditableEntity()
