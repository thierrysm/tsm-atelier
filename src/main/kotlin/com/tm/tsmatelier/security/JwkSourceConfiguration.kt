package com.tm.tsmatelier.security

import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import com.nimbusds.jose.jwk.source.JWKSource
import com.nimbusds.jose.proc.SecurityContext
import com.tm.tsmatelier.config.jwt.JwtKeyProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import java.util.UUID

@Configuration
class JwkSourceConfiguration(
    private val jwtKeyProperties: JwtKeyProperties,
) {
    @Bean
    fun getJwkSource(): JWKSource<SecurityContext> =
        ImmutableJWKSet(
            JWKSet(
                RSAKey
                    .Builder(jwtKeyProperties.public)
                    .privateKey(jwtKeyProperties.private)
                    .keyID(UUID.randomUUID().toString())
                    .build(),
            ),
        )

    @Bean
    fun getJwtEncoder(jwkSource: JWKSource<SecurityContext>): JwtEncoder = NimbusJwtEncoder(jwkSource)

    @Bean
    fun getJwtDecoder(): JwtDecoder = NimbusJwtDecoder.withPublicKey(jwtKeyProperties.public).build()
}
