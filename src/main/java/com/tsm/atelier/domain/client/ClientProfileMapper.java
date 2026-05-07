package com.tsm.atelier.domain.client;

import com.tsm.atelier.domain.client.dto.v1.request.AddressRequestDTO;
import com.tsm.atelier.domain.client.dto.v1.response.AddressResponseDTO;
import com.tsm.atelier.domain.client.dto.v1.response.ClientProfileResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientProfileMapper {

  @Mapping(target = "id", ignore = true)
  Address toEntity(AddressRequestDTO requestDTO);

  ClientProfileResponseDTO toResponse(ClientProfile clientProfile);

  AddressResponseDTO toAddressResponse(Address address);
}
