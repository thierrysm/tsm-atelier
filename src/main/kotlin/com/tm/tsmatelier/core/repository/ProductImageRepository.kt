package com.tm.tsmatelier.core.repository

import com.tm.tsmatelier.core.entity.ProductImageEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ProductImageRepository : JpaRepository<ProductImageEntity, UUID>
