package com.tm.tsmatelier.core.repository

import com.tm.tsmatelier.core.entity.RefreshTokenEntity
import com.tm.tsmatelier.core.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional
import java.util.UUID

interface RefreshTokenRepository : JpaRepository<RefreshTokenEntity, UUID> {
    fun findByToken(token: String): Optional<RefreshTokenEntity>

    @Query("SELECT t FROM RefreshTokenEntity t WHERE t.user = :user AND t.revoked = false")
    fun findAllValidTokensByUser(user: UserEntity): List<RefreshTokenEntity>

    fun findByUser(user: UserEntity): Optional<RefreshTokenEntity>
}
