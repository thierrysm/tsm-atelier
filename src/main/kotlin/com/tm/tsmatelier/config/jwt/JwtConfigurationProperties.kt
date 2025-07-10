package com.tm.tsmatelier.config.jwt

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated

@ConfigurationProperties(prefix = "spring.application.security.jwt.configuration")
@Validated
data class JwtConfigurationProperties(
    @field:NotNull
    @field:Positive
    val accessTokenExpirationMs: Long,
    @field:NotNull
    @field:Positive
    val refreshTokenExpirationMs: Long,
    @field:NotBlank
    val issuer: String,
)
