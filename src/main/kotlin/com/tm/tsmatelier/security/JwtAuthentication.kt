package com.tm.tsmatelier.security

import com.tm.tsmatelier.common.StringConstant.EMPTY
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

class JwtAuthentication(
    val jwt: String? = null,
    private val authenticated: Boolean = false,
    private val authenticatedUser: AuthenticatedUser? = null,
) : Authentication {
    override fun getName() = authenticatedUser?.username ?: EMPTY

    override fun getAuthorities(): Collection<GrantedAuthority> = authenticatedUser?.authorities ?: mutableListOf()

    override fun getCredentials() = null

    override fun getDetails() = null

    override fun getPrincipal() = authenticatedUser

    override fun isAuthenticated() = authenticated

    override fun setAuthenticated(isAuthenticated: Boolean): Unit = throw UnsupportedOperationException("Not supported")
}
