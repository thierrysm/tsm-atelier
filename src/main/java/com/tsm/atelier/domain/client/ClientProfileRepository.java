package com.tsm.atelier.domain.client;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientProfileRepository extends JpaRepository<ClientProfile, Long> {
  Optional<ClientProfile> findByUserId(UUID userId);

  boolean existsByCpfAndUserIdNot(String cpf, UUID userId);
}
