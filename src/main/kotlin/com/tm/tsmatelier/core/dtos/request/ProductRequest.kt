package com.tm.tsmatelier.core.dtos.request

import com.tm.tsmatelier.core.entity.enums.ProductCategoryEnum
import com.tm.tsmatelier.core.entity.enums.ProductSizeEnum
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import java.math.BigDecimal

data class ProductRequest(
    @field:NotBlank
    val name: String,
    @field:NotBlank
    val description: String,
    @field:NotNull
    @field:Positive
    val price: BigDecimal,
    @field:NotBlank
    @field:Size(max = 30)
    val sku: String,
    @field:NotNull
    val category: ProductCategoryEnum,
    val materials: Set<String> = setOf(),
    val careInstructions: Set<String> = setOf(),
    @field:NotEmpty(message = "Product must have at least one variant")
    val variants: List<ProductVariantRequest>,
    @field:NotEmpty(message = "Product must have at least one image")
    val images: List<ProductImageRequest>,
) {
    data class ProductImageRequest(
        @field:NotBlank
        val imageUrl: String,
        val altText: String?,
        @field:NotNull
        val isPrimary: Boolean,
    )

    data class ProductVariantRequest(
        @field:NotBlank
        @field:Size(max = 30)
        val sku: String,
        @field:NotNull
        val size: ProductSizeEnum,
        @field:NotBlank
        @field:Size(max = 40)
        val color: String,
        @field:NotNull
        @field:Min(0)
        val quantityInStock: Int,
    )
}
