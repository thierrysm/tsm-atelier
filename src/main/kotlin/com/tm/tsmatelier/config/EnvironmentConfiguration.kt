package com.tm.tsmatelier.config

import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

@Configuration
class EnvironmentConfiguration(
    private val environment: Environment,
) {
    fun isProduction() = environment.activeProfiles.contains(PRODUCTION_PROFILE_NAME)
}

private const val PRODUCTION_PROFILE_NAME = "prod"
