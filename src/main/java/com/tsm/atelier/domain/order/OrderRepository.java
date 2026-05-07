package com.tsm.atelier.domain.order;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, UUID> {
  Page<Order> findAllByUserIdOrderByCreatedAtDesc(UUID userId, Pageable pageable);

  Optional<Order> findByIdAndUserId(UUID id, UUID userId);
}
