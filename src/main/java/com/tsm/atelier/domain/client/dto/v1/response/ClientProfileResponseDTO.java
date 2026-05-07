package com.tsm.atelier.domain.client.dto.v1.response;

import java.util.List;

public record ClientProfileResponseDTO(
    Long id,
    String firstName,
    String lastName,
    String cpf,
    String phone,
    String email,
    List<AddressResponseDTO> addresses) {}
