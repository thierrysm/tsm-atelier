package com.tsm.atelier.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.features")
public record FeatureProperties(boolean emailVerificationEnabled) {}
