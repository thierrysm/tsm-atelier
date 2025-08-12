package com.tm.tsmatelier.core.dtos.reponse

import com.tm.tsmatelier.core.entity.enums.ProductCategoryEnum
import com.tm.tsmatelier.core.entity.enums.ProductSizeEnum
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class ProductResponse(
    val id: UUID,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val promotionalPrice: BigDecimal?,
    val sku: String,
    val category: ProductCategoryEnum,
    val isActive: Boolean,
    val materials: Set<String>,
    val careInstructions: Set<String>,
    val variants: List<ProductVariantResponse>,
    val images: List<ProductImageResponse>,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    data class ProductImageResponse(
        val id: UUID,
        val imageUrl: String,
        val altText: String?,
        val isPrimary: Boolean,
    )

    data class ProductVariantResponse(
        val id: UUID,
        val sku: String,
        val size: ProductSizeEnum,
        val color: String,
        val quantityInStock: Int,
    )
}
