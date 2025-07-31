package com.tm.tsmatelier.core.entity

import com.tm.tsmatelier.core.entity.enums.ProductCategoryEnum
import jakarta.persistence.CascadeType
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.OrderBy
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "products")
class ProductEntity(
    @Column(nullable = false, unique = true)
    var name: String,
    @Column(columnDefinition = "TEXT")
    var description: String,
    @Column(nullable = false, precision = 10, scale = 2)
    var price: BigDecimal,
    @Column(nullable = false, unique = true, length = 20)
    var sku: String,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var category: ProductCategoryEnum,
    @Column(nullable = false)
    var isActive: Boolean = true,
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_materials", joinColumns = [JoinColumn(name = "product_id")])
    @Column(name = "material")
    var materials: MutableSet<String> = mutableSetOf(),
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_care_instructions", joinColumns = [JoinColumn(name = "product_id")])
    @Column(name = "instruction")
    var careInstructions: MutableSet<String> = mutableSetOf(),
    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    var variants: MutableList<ProductVariantEntity> = mutableListOf(),
    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("isPrimary DESC")
    var images: MutableList<ProductImageEntity> = mutableListOf(),
) : AuditableEntity()
