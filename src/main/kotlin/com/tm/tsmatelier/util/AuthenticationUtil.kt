package com.tm.tsmatelier.util

import com.tm.tsmatelier.security.AuthenticatedUser
import org.springframework.security.core.Authentication

fun Authentication.getUser() = (this.principal as AuthenticatedUser).user
