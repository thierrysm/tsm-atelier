package com.tm.tsmatelier.security

import com.tm.tsmatelier.core.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user =
            userRepository.findByEmail(username)
                ?: throw UsernameNotFoundException("User not found with email: $username")

        val authorities = user.roles.map { SimpleGrantedAuthority(it.name) }.toSet()

        return User(user.email, user.password, authorities)
    }
}
