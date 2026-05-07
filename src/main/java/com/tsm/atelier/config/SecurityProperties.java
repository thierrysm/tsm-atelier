package com.tsm.atelier.config;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.security.jwt")
public record SecurityProperties(
    String secret,
    Long accessTokenExpiration,
    Long refreshTokenExpiration,
    String issuer,
    String audience,
    List<String> allowedOrigins) {}
