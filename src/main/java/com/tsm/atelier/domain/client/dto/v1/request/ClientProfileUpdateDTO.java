package com.tsm.atelier.domain.client.dto.v1.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Optional;

public record ClientProfileUpdateDTO(
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
        Optional<String> firstName,
    @Size(min = 2, max = 100, message = "Sobrenome deve ter entre 2 e 100 caracteres")
        Optional<String> lastName,
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF inválido")
        Optional<String> cpf,
    @Pattern(regexp = "\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}", message = "Telefone inválido")
        Optional<String> phone) {}
