package com.tsm.atelier.domain.product.repository;

import com.tsm.atelier.domain.product.ProductComposition;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCompositionRepository extends JpaRepository<ProductComposition, Long> {
  @EntityGraph(attributePaths = {"materials"})
  List<ProductComposition> findAllByProductId(Long productId);
}
