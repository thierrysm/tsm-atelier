package com.tsm.atelier.domain.client.dto.v1.response;

public record AddressResponseDTO(
    Long id,
    String cep,
    String street,
    String number,
    String complement,
    String neighborhood,
    String city,
    String state,
    Boolean isDefault) {}
