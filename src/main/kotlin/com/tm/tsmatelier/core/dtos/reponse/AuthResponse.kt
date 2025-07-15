package com.tm.tsmatelier.core.dtos.reponse

data class AuthResponse(
    val accessToken: String,
    val refreshToken: String,
)
