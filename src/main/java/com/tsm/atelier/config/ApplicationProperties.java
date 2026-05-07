package com.tsm.atelier.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "spring.application")
public record ApplicationProperties(
    @NotNull @Positive Long emailVerificationExpiration,
    @NotBlank String verificationBaseUrl,
    @NotBlank String verificationSubject) {}
