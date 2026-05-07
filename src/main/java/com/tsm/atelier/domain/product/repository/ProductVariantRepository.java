package com.tsm.atelier.domain.product.repository;

import com.tsm.atelier.domain.product.ProductSize;
import com.tsm.atelier.domain.product.ProductVariant;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
  boolean existsByProductColorIdAndSize(Long productColorId, ProductSize productSize);

  Optional<ProductVariant> findByIdAndProductColorIdAndProductColorProductId(
      Long id, Long productColorId, Long productId);

  @EntityGraph(attributePaths = {"productColor", "productColor.product", "productColor.images"})
  List<ProductVariant> findAllWithDetailsByIdIn(Collection<Long> ids);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
  @EntityGraph(attributePaths = {"productColor", "productColor.product", "productColor.images"})
  List<ProductVariant> findLockedWithDetailsByIdIn(Collection<Long> ids);
}
