package com.tsm.atelier.domain.auth.repository;

import com.tsm.atelier.domain.auth.EmailVerificationToken;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmailVerificationTokenRepository
    extends JpaRepository<EmailVerificationToken, Long> {
  Optional<EmailVerificationToken> findByToken(String token);

  @Modifying(clearAutomatically = true, flushAutomatically = true)
  @Query(
      "UPDATE EmailVerificationToken t SET t.used = true "
          + "WHERE t.user.id = :userId AND t.used = false")
  void invalidateActiveByUserId(@Param("userId") UUID userId);
}
