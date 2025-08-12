package com.tm.tsmatelier.core.repository

import com.tm.tsmatelier.core.entity.CollectionEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CollectionRepository : JpaRepository<CollectionEntity, UUID> {
    fun existsBySlug(slug: String): Boolean
}
