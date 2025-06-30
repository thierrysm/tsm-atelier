package com.tm.tsmatelier.core.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "addresses")
class AddressEntity(
    @Column(nullable = false, length = 200)
    var street: String,
    @Column(nullable = false, length = 20)
    var number: String,
    @Column(length = 100)
    var complement: String?,
    @Column(nullable = false, length = 100)
    var neighborhood: String,
    @Column(nullable = false, length = 100)
    var city: String,
    @Column(nullable = false, length = 2)
    var state: String,
    @Column(nullable = false, length = 8)
    var zipCode: String,
    @Column(nullable = false)
    var isPrimary: Boolean = false,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: UserEntity? = null,
) : AuditableEntity()
