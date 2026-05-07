package com.tsm.atelier.domain.client;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address, Long> {

  Optional<Address> findByIdAndClientProfileUserId(Long id, UUID userId);

  @Modifying
  @Query("UPDATE Address a SET a.isDefault = false WHERE a.clientProfile.id = :profileId")
  void removeDefaultFromProfile(@Param("profileId") Long profileId);
}
