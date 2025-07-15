package com.tm.tsmatelier.config.jwt

import jakarta.validation.constraints.NotNull
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

@ConfigurationProperties(prefix = "spring.application.security.jwt.key")
@Validated
data class JwtKeyProperties(
    @field:NotNull
    val private: RSAPrivateKey,
    @field:NotNull
    val public: RSAPublicKey,
)
