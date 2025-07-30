package com.tm.tsmatelier.core.repository

import com.tm.tsmatelier.core.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ProductRepository : JpaRepository<ProductEntity, UUID> {
    fun existsByName(name: String): Boolean

    fun existsBySku(sku: String): Boolean
}
