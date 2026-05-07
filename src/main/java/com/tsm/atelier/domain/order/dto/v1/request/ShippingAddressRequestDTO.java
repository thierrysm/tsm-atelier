package com.tsm.atelier.domain.order.dto.v1.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ShippingAddressRequestDTO(
    @NotBlank @Size(max = 120) String recipientName,
    @NotBlank @Pattern(regexp = "\\d{5}-?\\d{3}") String cep,
    @NotBlank @Size(max = 255) String street,
    @NotBlank @Size(max = 20) String number,
    @Size(max = 100) String complement,
    @NotBlank @Size(max = 100) String neighborhood,
    @NotBlank @Size(max = 100) String city,
    @NotBlank @Size(min = 2, max = 2) String state) {}
