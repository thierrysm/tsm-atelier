package com.tm.tsmatelier.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated

@ConfigurationProperties(prefix = "security.allowed-paths")
@Validated
data class AllowedPathsProperties(
    val get: List<String> = emptyList(),
    val post: List<String> = emptyList(),
)
