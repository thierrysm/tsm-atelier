package com.tm.tsmatelier.core.repository

import com.tm.tsmatelier.core.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ProductRepository : JpaRepository<ProductEntity, UUID> {
    fun existsByName(name: String): Boolean

    fun existsBySku(sku: String): Boolean

    fun findByCollectionSlugIgnoreCase(collectionSlug: String): List<ProductEntity>

    @Query(
        """
    SELECT COUNT(p) > 0
    FROM ProductEntity p
    WHERE p.name = :name
       OR p.sku = :sku
       OR EXISTS (
            SELECT 1
            FROM ProductVariantEntity v
            WHERE v.sku IN :variantSkus
       )
    """,
    )
    fun existsByNameOrSkuOrVariantSku(
        name: String,
        sku: String,
        variantSkus: List<String>,
    ): Boolean
}
