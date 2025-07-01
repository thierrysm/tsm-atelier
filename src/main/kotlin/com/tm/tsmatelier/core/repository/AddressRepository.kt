package com.tm.tsmatelier.core.repository

import com.tm.tsmatelier.core.entity.AddressEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface AddressRepository : JpaRepository<AddressEntity, UUID>
