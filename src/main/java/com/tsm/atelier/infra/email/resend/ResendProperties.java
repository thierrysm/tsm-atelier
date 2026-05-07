package com.tsm.atelier.infra.email.resend;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "spring.resend")
public record ResendProperties(@NotBlank String apiKey, @NotBlank String from) {}
