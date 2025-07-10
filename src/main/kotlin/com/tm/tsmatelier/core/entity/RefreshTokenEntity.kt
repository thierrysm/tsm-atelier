package com.tm.tsmatelier.core.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "refresh_tokens")
class RefreshTokenEntity(
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    var user: UserEntity,
    @Column(nullable = false, unique = true)
    var token: String,
    @Column(nullable = false)
    var expiryDate: Instant,
    @Column(nullable = false)
    var revoked: Boolean = false,
) : AuditableEntity()
