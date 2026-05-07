package com.tsm.atelier.domain.client.dto.v1.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressRequestDTO(
    @NotBlank(message = "CEP é obrigatório")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP inválido")
        String cep,
    @NotBlank(message = "Rua é obrigatória") String street,
    @NotBlank(message = "Número é obrigatório") String number,
    String complement,
    @NotBlank(message = "Bairro é obrigatório") String neighborhood,
    @NotBlank(message = "Cidade é obrigatória") String city,
    @NotBlank(message = "Estado é obrigatório")
        @Size(min = 2, max = 2, message = "Estado deve ter 2 caracteres")
        String state,
    @NotNull(message = "Campo obrigatório") Boolean isDefault) {}
