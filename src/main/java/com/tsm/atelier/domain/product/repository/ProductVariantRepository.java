package com.tsm.atelier.domain.product.repository;

import com.tsm.atelier.domain.product.ProductSize;
import com.tsm.atelier.domain.product.ProductVariant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
  boolean existsByProductColorIdAndSize(Long productColorId, ProductSize productSize);

  Optional<ProductVariant> findByIdAndProductColorIdAndProductColorProductId(
      Long id, Long productColorId, Long productId);
}
