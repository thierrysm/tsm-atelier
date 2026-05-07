package com.tsm.atelier.domain.auth.dto.v1.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
    @NotBlank(message = "Email é obrigatório") @Email(message = "Email inválido") String email,
    @NotBlank(message = "Senha é obrigatória") String password) {}
