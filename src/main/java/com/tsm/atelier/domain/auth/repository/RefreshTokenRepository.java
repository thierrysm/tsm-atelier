package com.tsm.atelier.domain.auth.repository;

import com.tsm.atelier.domain.auth.RefreshToken;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByTokenHash(String tokenHash);

  @Modifying
  @Query("UPDATE RefreshToken r SET r.revoked = true WHERE r.user.id = :userId")
  void revokeAllByUserId(UUID userId);

  @Modifying
  @Query(
      "UPDATE RefreshToken r SET r.revoked = true WHERE r.familyId = :familyId AND r.revoked = false")
  void revokeFamily(UUID familyId);
}
