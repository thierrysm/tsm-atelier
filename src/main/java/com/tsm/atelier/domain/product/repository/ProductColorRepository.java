package com.tsm.atelier.domain.product.repository;

import com.tsm.atelier.domain.product.ProductColor;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductColorRepository extends JpaRepository<ProductColor, Long> {
  @EntityGraph(attributePaths = {"images", "variants"})
  List<ProductColor> findAllByProductId(Long productId);

  Optional<ProductColor> findByIdAndProductId(Long id, Long productId);
}
