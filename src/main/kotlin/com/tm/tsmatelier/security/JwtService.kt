package com.tm.tsmatelier.security

import com.tm.tsmatelier.config.jwt.JwtConfigurationProperties
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class JwtService(
    private val jwtEncoder: JwtEncoder,
    private val jwtConfigurationProperties: JwtConfigurationProperties,
) {
    fun generateAccessToken(authentication: Authentication): String {
        val now = Instant.now()
        val expirationTime = now.plusMillis(jwtConfigurationProperties.accessTokenExpirationMs)

        val authorities = authentication.authorities.map { it.authority }.toSet()

        val claims =
            JwtClaimsSet.builder()
                .issuer(jwtConfigurationProperties.issuer)
                .issuedAt(now)
                .expiresAt(expirationTime)
                .subject(authentication.name)
                .claim("roles", authorities)
                .build()

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).tokenValue
    }
}
