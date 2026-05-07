package com.tsm.atelier.infra.payment.stripe;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "payment.stripe")
public record StripeProperties(
    @NotBlank String apiKey,
    @NotBlank String webhookSecret,
    @NotBlank String successUrl,
    @NotBlank String cancelUrl) {}
