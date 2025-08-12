package com.tm.tsmatelier.core.dtos.reponse

import java.time.LocalDateTime
import java.util.UUID

data class CollectionResponse(
    val id: UUID,
    val name: String,
    val slug: String,
    val description: String?,
    val isActive: Boolean,
    val createdAt: LocalDateTime,
)
