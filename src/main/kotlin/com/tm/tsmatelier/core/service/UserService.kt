package com.tm.tsmatelier.core.service

import com.tm.tsmatelier.core.dtos.request.RegisterRequest
import com.tm.tsmatelier.core.entity.UserEntity
import com.tm.tsmatelier.core.entity.enums.Role
import com.tm.tsmatelier.core.exception.UserAlreadyExistsException
import com.tm.tsmatelier.core.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    @Transactional
    fun registerUser(request: RegisterRequest): UserEntity {
        if (userRepository.existsByEmail(request.email)) {
            throw UserAlreadyExistsException("Error: Email is already in use!")
        }
        if (userRepository.existsByCpf(request.cpf)) {
            throw UserAlreadyExistsException("Error: CPF is already in use!")
        }

        val newUser =
            UserEntity(
                fullName = request.fullName,
                cpf = request.cpf,
                email = request.email,
                password = passwordEncoder.encode(request.password),
                phone = request.phone,
                roles = mutableSetOf(Role.CUSTOMER),
            )

        return userRepository.save(newUser)
    }
}
