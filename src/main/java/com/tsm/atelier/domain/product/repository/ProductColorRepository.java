package com.tsm.atelier.domain.product.repository;

import com.tsm.atelier.domain.product.ProductColor;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductColorRepository extends JpaRepository<ProductColor, Long> {

  Optional<ProductColor> findByIdAndProductId(Long id, Long productId);

  boolean existsByIdAndProductId(Long id, Long productId);

  boolean existsByProductIdAndNameIgnoreCase(Long productId, String name);

  boolean existsByProductIdAndHexCodeIgnoreCase(Long productId, String hexCode);

  boolean existsByProductIdAndNameIgnoreCaseAndIdNot(Long productId, String name, Long id);

  boolean existsByProductIdAndHexCodeIgnoreCaseAndIdNot(Long productId, String hexCode, Long id);
}
