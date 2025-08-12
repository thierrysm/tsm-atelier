package com.tm.tsmatelier.core.dtos.request

import com.tm.tsmatelier.core.entity.enums.ProductCategoryEnum
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.math.BigDecimal
import java.util.UUID

data class UpdateProductRequest(
    val name: String?,
    val description: String?,
    @field:Positive
    val price: BigDecimal?,
    @field:Positive
    val promotionalPrice: BigDecimal?,
    val category: ProductCategoryEnum?,
    val isActive: Boolean?,
    val materials: Set<String>?,
    val careInstructions: Set<String>?,
    val variants: List<UpdateProductVariantRequest>?,
    val version: Long?,
) {
    data class UpdateProductVariantRequest(
        @field:NotNull
        val id: UUID,
        @field:Min(0)
        val quantityInStock: Int?,
        // TODO (Outros campos como SKU, tamanho, cor, poderiam ser adicionados se pudessem ser alterados)
    )
}
