package com.tsm.atelier.domain.client.controler.v1;

import com.tsm.atelier.domain.client.ClientProfileService;
import com.tsm.atelier.domain.client.dto.v1.request.AddressRequestDTO;
import com.tsm.atelier.domain.client.dto.v1.request.ClientProfileUpdateDTO;
import com.tsm.atelier.domain.client.dto.v1.response.AddressResponseDTO;
import com.tsm.atelier.domain.client.dto.v1.response.ClientProfileResponseDTO;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/client/profile")
@RequiredArgsConstructor
public class ClientProfileController {

  private final ClientProfileService clientProfileService;

  @GetMapping
  public ResponseEntity<ClientProfileResponseDTO> findMyProfile() {
    return ResponseEntity.ok(clientProfileService.findMyProfile());
  }

  @PatchMapping
  public ResponseEntity<ClientProfileResponseDTO> updateMyProfile(
      @Valid @RequestBody ClientProfileUpdateDTO request) {
    return ResponseEntity.ok(clientProfileService.updateMyProfile(request));
  }

  @PostMapping("/addresses")
  public ResponseEntity<AddressResponseDTO> addAddress(
      @Valid @RequestBody AddressRequestDTO request) {
    AddressResponseDTO response = clientProfileService.addAddress(request);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(response.id())
            .toUri();
    return ResponseEntity.created(location).body(response);
  }

  @DeleteMapping("/addresses/{addressId}")
  public ResponseEntity<Void> removeAddress(@PathVariable Long addressId) {
    clientProfileService.removeAddress(addressId);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/addresses/{addressId}/default")
  public ResponseEntity<AddressResponseDTO> setDefaultAddress(@PathVariable Long addressId) {
    return ResponseEntity.ok(clientProfileService.setDefaultAddress(addressId));
  }
}
