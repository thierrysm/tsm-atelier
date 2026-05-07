package com.tsm.atelier.domain.product.repository;

import com.tsm.atelier.domain.product.ProductImage;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

  Optional<ProductImage> findByIdAndProductColorId(Long id, Long productColorId);

  boolean existsByIdAndProductColorId(Long id, Long productColorId);

  Optional<ProductImage> findFirstByProductColorIdAndIdNotOrderByDisplayOrderAsc(
      Long productColorId, Long excludedId);

  int countByProductColorId(Long productColorId);

  @Modifying(clearAutomatically = true, flushAutomatically = true)
  @Query("UPDATE ProductImage i SET i.isCover = false WHERE i.productColor.id = :colorId")
  void removeCoversFromColor(@Param("colorId") Long colorId);

  @Modifying(clearAutomatically = true, flushAutomatically = true)
  @Query(
      "UPDATE ProductImage i SET i.isCover = false "
          + "WHERE i.productColor.id = :colorId AND i.id <> :imageId")
  void clearCoversExcept(@Param("colorId") Long colorId, @Param("imageId") Long imageId);

  @Modifying(clearAutomatically = true, flushAutomatically = true)
  @Query("UPDATE ProductImage i SET i.isCover = true WHERE i.id = :imageId")
  void markAsCover(@Param("imageId") Long imageId);
}
