package com.tm.tsmatelier.security

import com.tm.tsmatelier.core.entity.UserEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AuthenticatedUser(
    val user: UserEntity,
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> = user.roles.map { role -> SimpleGrantedAuthority(role.name) }.toSet()

    override fun getPassword(): String = user.password

    override fun getUsername(): String = user.email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
