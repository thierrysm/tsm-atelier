package com.tsm.atelier.domain.product;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductColorRepository extends JpaRepository<ProductColor, UUID> {
  @EntityGraph(attributePaths = {"images", "variants"})
  List<ProductColor> findAllByProductIdIn(List<UUID> productIds);
}
