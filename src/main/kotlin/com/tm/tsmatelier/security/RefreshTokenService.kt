package com.tm.tsmatelier.security

import com.tm.tsmatelier.config.jwt.JwtConfigurationProperties
import com.tm.tsmatelier.core.dtos.reponse.AuthResponse
import com.tm.tsmatelier.core.entity.RefreshTokenEntity
import com.tm.tsmatelier.core.entity.UserEntity
import com.tm.tsmatelier.core.exception.RefreshTokenExpiredException
import com.tm.tsmatelier.core.exception.TokenCompromisedException
import com.tm.tsmatelier.core.repository.RefreshTokenRepository
import com.tm.tsmatelier.core.repository.UserRepository
import com.tm.tsmatelier.util.orThrow
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.util.UUID

@Service
class RefreshTokenService(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val userRepository: UserRepository,
    private val jwtService: JwtService,
    private val userDetailsService: UserDetailsService,
    private val jwtConfigurationProperties: JwtConfigurationProperties,
    private val tokenRevocationService: TokenRevocationService,
) {
    @Transactional
    fun processRefreshToken(providedToken: String): AuthResponse {
        val refreshToken =
            refreshTokenRepository
                .findByToken(providedToken)

        if (refreshToken.orThrow().revoked) {
            tokenRevocationService.revokeAllTokensForUser(refreshToken.orThrow().user)
            throw TokenCompromisedException()
        }

        if (refreshToken.orThrow().expiryDate.isBefore(Instant.now())) {
            refreshTokenRepository.delete(refreshToken.orThrow())
            throw RefreshTokenExpiredException()
        }

        refreshToken.orThrow().revoked = true

        return createNewSessionFor(refreshToken.orThrow().user)
    }

    @Transactional
    fun createNewRefreshTokenForUser(username: String): RefreshTokenEntity {
        val user =
            userRepository.findByEmail(username)
                ?: throw UsernameNotFoundException("User not found with email: $username")

        tokenRevocationService.revokeAllTokensForUser(user)

        return createAndSaveNewTokenFor(user)
    }

    private fun createNewSessionFor(user: UserEntity): AuthResponse {
        val userDetails = userDetailsService.loadUserByUsername(user.email)

        val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)

        val newAccessToken = jwtService.generateAccessToken(authentication)

        val newRefreshToken = createAndSaveNewTokenFor(user)

        return AuthResponse(
            accessToken = newAccessToken,
            refreshToken = newRefreshToken.token,
        )
    }

    @Transactional
    fun logout(providedToken: String) {
        refreshTokenRepository.findByToken(providedToken)?.let { token ->
            token.revoked = true
        }
    }

    private fun createAndSaveNewTokenFor(user: UserEntity): RefreshTokenEntity {
        val newRefreshToken =
            RefreshTokenEntity(
                user = user,
                token = UUID.randomUUID().toString(),
                expiryDate = Instant.now().plusMillis(jwtConfigurationProperties.refreshTokenExpirationMs),
                revoked = false,
            )
        return refreshTokenRepository.save(newRefreshToken)
    }
}
