package com.tsm.atelier.domain.product.repository;

import com.tsm.atelier.domain.product.Product;
import com.tsm.atelier.domain.product.ProductStatus;
import com.tsm.atelier.domain.product.dto.v1.internal.ProductIntegrityDTO;
import java.time.Instant;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query(
      """
      SELECT new com.tsm.atelier.domain.product.dto.v1.internal.ProductIntegrityDTO(
          p.id,
          (SELECT COUNT(c) FROM ProductColor c WHERE c.product.id = :id),
          (SELECT COUNT(v) FROM ProductVariant v WHERE v.productColor.product.id = :id),
          (SELECT COUNT(i) FROM ProductImage i WHERE i.productColor.product.id = :id),
          (SELECT COUNT(DISTINCT i.productColor.id) FROM ProductImage i WHERE i.productColor.product.id = :id AND i.isCover = true),
          (SELECT COUNT(i) FROM ProductImage i WHERE i.productColor.product.id = :id AND i.isCover = true),
          (SELECT COUNT(ci) FROM ProductCare ci WHERE ci.product.id = :id),
          (SELECT COUNT(comp) FROM ProductComposition comp WHERE comp.product.id = :id)
      )
      FROM Product p
      WHERE p.id = :id
  """)
  Optional<ProductIntegrityDTO> findIntegrityById(@Param("id") Long id);

  @EntityGraph(attributePaths = {"colors", "colors.images", "colors.variants"})
  Slice<Product> findAllByStatus(ProductStatus status, Pageable pageable);

  boolean existsByName(String name);

  boolean existsByNameAndStatus(String name, ProductStatus status);

  boolean existsBySlug(String alias);

  @EntityGraph(attributePaths = {"collection", "colors", "colors.images", "colors.variants"})
  Optional<Product> findBySlugAndStatus(String slug, ProductStatus status);

  @EntityGraph(attributePaths = {"collection", "colors", "colors.images", "colors.variants"})
  Optional<Product> findProductDetailsById(Long id);

  @EntityGraph(attributePaths = {"colors", "colors.images", "colors.variants"})
  Slice<Product> findByCollectionId(Long collectionId, Pageable pageable);

  @Modifying(clearAutomatically = true)
  @Query(
      "UPDATE Product p SET p.status = 'ARCHIVED', p.disabledAt = :disabledAt WHERE p.collection.id = :collectionId")
  void archiveAllByCollectionId(
      @Param("collectionId") Long collectionId, @Param("disabledAt") Instant disabledAt);
}
