package com.tm.tsmatelier.core.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "collections")
class CollectionEntity(
    @Column(nullable = false, unique = true)
    var name: String,
    @Column(nullable = false, unique = true)
    var slug: String,
    @Column(columnDefinition = "TEXT")
    var description: String?,
    @Column(nullable = false)
    var isActive: Boolean = true,
    @OneToMany(mappedBy = "collection", cascade = [CascadeType.PERSIST], fetch = FetchType.LAZY)
    var products: MutableList<ProductEntity> = mutableListOf(),
) : AuditableEntity()
