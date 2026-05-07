package com.tsm.atelier.domain.client;

import com.tsm.atelier.domain.auth.User;
import com.tsm.atelier.domain.client.dto.v1.request.AddressRequestDTO;
import com.tsm.atelier.domain.client.dto.v1.request.ClientProfileUpdateDTO;
import com.tsm.atelier.domain.client.dto.v1.response.AddressResponseDTO;
import com.tsm.atelier.domain.client.dto.v1.response.ClientProfileResponseDTO;
import com.tsm.atelier.exception.EntityAlreadyExistsException;
import com.tsm.atelier.exception.EntityNotFoundException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientProfileService {

  private final ClientProfileRepository clientProfileRepository;
  private final AddressRepository addressRepository;
  private final ClientProfileMapper clientProfileMapper;

  // busca o perfil do usuário autenticado
  @Transactional(readOnly = true)
  public ClientProfileResponseDTO findMyProfile() {
    User user = getAuthenticatedUser();

    ClientProfile profile =
        clientProfileRepository
            .findByUserId(user.getId())
            .orElseThrow(() -> new EntityNotFoundException("Perfil", "userId", user.getId()));

    return clientProfileMapper.toResponse(profile);
  }

  // atualiza o perfil do usuário autenticado
  @Transactional
  public ClientProfileResponseDTO updateMyProfile(ClientProfileUpdateDTO request) {
    User user = getAuthenticatedUser();

    ClientProfile profile =
        clientProfileRepository
            .findByUserId(user.getId())
            .orElseThrow(() -> new EntityNotFoundException("Perfil", "userId", user.getId()));

    // valida CPF duplicado
    request
        .cpf()
        .ifPresent(
            cpf -> {
              if (clientProfileRepository.existsByCpfAndUserIdNot(cpf, user.getId())) {
                throw new EntityAlreadyExistsException("Perfil", "cpf", cpf);
              }
              profile.setCpf(cpf);
            });

    request.firstName().ifPresent(profile::setFirstName);
    request.lastName().ifPresent(profile::setLastName);
    request.phone().ifPresent(profile::setPhone);

    return clientProfileMapper.toResponse(clientProfileRepository.save(profile));
  }

  // adiciona endereço
  @Transactional
  public AddressResponseDTO addAddress(AddressRequestDTO request) {
    User user = getAuthenticatedUser();

    ClientProfile profile =
        clientProfileRepository
            .findByUserId(user.getId())
            .orElseThrow(() -> new EntityNotFoundException("Perfil", "userId", user.getId()));

    // se o novo endereço é padrão remove o padrão atual
    if (Boolean.TRUE.equals(request.isDefault())) {
      addressRepository.removeDefaultFromProfile(profile.getId());
    }

    Address address = clientProfileMapper.toEntity(request);
    address.setClientProfile(profile);

    return clientProfileMapper.toAddressResponse(addressRepository.save(address));
  }

  // remove endereço
  @Transactional
  public void removeAddress(Long addressId) {
    User user = getAuthenticatedUser();

    Address address =
        addressRepository
            .findByIdAndClientProfileUserId(addressId, user.getId())
            .orElseThrow(() -> new EntityNotFoundException("Endereço", "id", addressId));

    addressRepository.delete(address);
  }

  // define endereço padrão
  @Transactional
  public AddressResponseDTO setDefaultAddress(Long addressId) {
    User user = getAuthenticatedUser();

    ClientProfile profile =
        clientProfileRepository
            .findByUserId(user.getId())
            .orElseThrow(() -> new EntityNotFoundException("Perfil", "userId", user.getId()));

    // remove o padrão atual
    addressRepository.removeDefaultFromProfile(profile.getId());

    Address address =
        addressRepository
            .findByIdAndClientProfileUserId(addressId, user.getId())
            .orElseThrow(() -> new EntityNotFoundException("Endereço", "id", addressId));

    address.setIsDefault(true);
    return clientProfileMapper.toAddressResponse(addressRepository.save(address));
  }

  // helper — pega o usuário autenticado do contexto do Spring Security
  private User getAuthenticatedUser() {
    return (User)
        Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication())
            .getPrincipal();
  }
}
