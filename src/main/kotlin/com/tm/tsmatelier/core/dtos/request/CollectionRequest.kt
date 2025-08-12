package com.tm.tsmatelier.core.dtos.request

import jakarta.validation.constraints.NotBlank

data class CollectionRequest(
    @field:NotBlank
    val name: String,
    val description: String?,
)
