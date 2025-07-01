package com.tm.tsmatelier.core.entity

import com.tm.tsmatelier.core.entity.enums.Role
import jakarta.persistence.CascadeType
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class UserEntity(
    @Column(nullable = false)
    var fullName: String,
    @Column(nullable = false, unique = true, length = 11)
    var cpf: String,
    @Column(nullable = false, unique = true)
    var email: String,
    @Column(nullable = false)
    var password: String,
    @Column(nullable = false, length = 15)
    var phone: String,
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")],
    )
    @Column(name = "role")
    var roles: MutableSet<Role> = mutableSetOf(Role.CUSTOMER),
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var addresses: MutableList<AddressEntity> = mutableListOf(),
) : AuditableEntity()
