package com.tm.tsmatelier.security

import com.tm.tsmatelier.core.entity.UserEntity
import com.tm.tsmatelier.core.repository.RefreshTokenRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class TokenRevocationService(
    private val refreshTokenRepository: RefreshTokenRepository,
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun revokeAllTokensForUser(user: UserEntity) {
        val validUserTokens = refreshTokenRepository.findAllValidTokensByUser(user)
        if (validUserTokens.isNotEmpty()) {
            validUserTokens.forEach { it.revoked = true }
        }
    }
}
