package com.tm.tsmatelier.core.repository

import com.tm.tsmatelier.core.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository : JpaRepository<UserEntity, UUID>
