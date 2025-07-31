package com.tm.tsmatelier.core.mapper

import com.tm.tsmatelier.core.dtos.reponse.ProductResponse
import com.tm.tsmatelier.core.dtos.request.ProductRequest
import com.tm.tsmatelier.core.entity.ProductEntity
import com.tm.tsmatelier.core.entity.ProductImageEntity
import com.tm.tsmatelier.core.entity.ProductVariantEntity
import org.springframework.stereotype.Component

@Component
class ProductMapper {
    fun toEntity(request: ProductRequest): ProductEntity {
        val newProduct =
            ProductEntity(
                name = request.name,
                description = request.description,
                price = request.price,
                sku = request.sku,
                category = request.category,
                materials = request.materials.toMutableSet(),
                careInstructions = request.careInstructions.toMutableSet(),
            )

        newProduct.variants =
            request.variants
                .map { variantDto ->
                    ProductVariantEntity(
                        sku = variantDto.sku,
                        size = variantDto.size,
                        color = variantDto.color,
                        quantityInStock = variantDto.quantityInStock,
                        product = newProduct,
                    )
                }.toMutableList()

        newProduct.images =
            request.images
                .map { imageDto ->
                    ProductImageEntity(
                        imageUrl = imageDto.imageUrl,
                        altText = imageDto.altText,
                        isPrimary = imageDto.isPrimary,
                        product = newProduct,
                    )
                }.toMutableList()

        return newProduct
    }

    fun toResponseDto(entity: ProductEntity): ProductResponse =
        ProductResponse(
            id = entity.id!!,
            name = entity.name,
            description = entity.description,
            price = entity.price,
            sku = entity.sku,
            category = entity.category,
            isActive = entity.isActive,
            materials = entity.materials,
            careInstructions = entity.careInstructions,
            variants =
                entity.variants.map { variant ->
                    ProductResponse.ProductVariantResponse(
                        id = variant.id!!,
                        sku = variant.sku,
                        size = variant.size,
                        color = variant.color,
                        quantityInStock = variant.quantityInStock,
                    )
                },
            images =
                entity.images.map { image ->
                    ProductResponse.ProductImageResponse(
                        id = image.id!!,
                        imageUrl = image.imageUrl,
                        altText = image.altText,
                        isPrimary = image.isPrimary,
                    )
                },
            createdAt = entity.createdAt!!,
            updatedAt = entity.updatedAt!!,
        )
}
