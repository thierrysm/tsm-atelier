package com.tm.tsmatelier.core.repository

import com.tm.tsmatelier.core.entity.ProductVariantEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ProductVariantRepository : JpaRepository<ProductVariantEntity, UUID> {
    fun existsBySku(sku: String): Boolean
}
