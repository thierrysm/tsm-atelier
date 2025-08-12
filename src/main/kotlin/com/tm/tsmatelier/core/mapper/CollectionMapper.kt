package com.tm.tsmatelier.core.mapper

import com.tm.tsmatelier.core.dtos.reponse.CollectionResponse
import com.tm.tsmatelier.core.entity.CollectionEntity
import org.springframework.stereotype.Component

@Component
class CollectionMapper {
    fun toResponseDto(entity: CollectionEntity): CollectionResponse =
        CollectionResponse(
            id = entity.id!!,
            name = entity.name,
            slug = entity.slug,
            description = entity.description,
            isActive = entity.isActive,
            createdAt = entity.createdAt!!,
        )
}
