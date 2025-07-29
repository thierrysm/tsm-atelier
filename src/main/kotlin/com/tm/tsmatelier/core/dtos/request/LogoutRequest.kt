package com.tm.tsmatelier.core.dtos.request

import jakarta.validation.constraints.NotBlank

data class LogoutRequest(
    @field:NotBlank
    val refreshToken: String,
)
