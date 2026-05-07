package com.tsm.atelier.domain.auth.dto.v1.request;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequestDTO(
    @NotBlank(message = "Refresh token é obrigatório") String refreshToken) {}
