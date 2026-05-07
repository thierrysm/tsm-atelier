package com.tsm.atelier.domain.product.repository;

import com.tsm.atelier.domain.product.Product;
import com.tsm.atelier.domain.product.ProductStatus;
import com.tsm.atelier.domain.product.dto.v1.internal.ProductIntegrityDTO;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository
    extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

  @Query(
      """
      SELECT new com.tsm.atelier.domain.product.dto.v1.internal.ProductIntegrityDTO(
          p.id,
          (SELECT COUNT(c) FROM ProductColor c WHERE c.product.id = :id),
          (SELECT COUNT(v) FROM ProductVariant v WHERE v.productColor.product.id = :id),
          (SELECT COUNT(i) FROM ProductImage i WHERE i.productColor.product.id = :id),
          (SELECT COUNT(DISTINCT i.productColor.id) FROM ProductImage i
              WHERE i.productColor.product.id = :id AND i.isCover = true)
      )
      FROM Product p
      WHERE p.id = :id
      """)
  Optional<ProductIntegrityDTO> findIntegrityById(@Param("id") Long id);

  @Query("SELECT p.id FROM Product p WHERE p.status = :status")
  Slice<Long> findIdsByStatus(@Param("status") ProductStatus status, Pageable pageable);

  @Query("SELECT p.id FROM Product p WHERE p.collection.id = :collectionId")
  Slice<Long> findIdsByCollectionId(@Param("collectionId") Long collectionId, Pageable pageable);

  @EntityGraph(attributePaths = {"colors", "colors.images", "colors.variants"})
  List<Product> findByIdIn(List<Long> ids);

  Optional<Product> findByName(String name);

  boolean existsByNameAndStatus(String name, ProductStatus status);

  boolean existsBySlug(String alias);

  @EntityGraph(attributePaths = {"collection", "colors", "colors.images", "colors.variants"})
  Optional<Product> findBySlugAndStatus(String slug, ProductStatus status);

  @EntityGraph(attributePaths = {"collection", "colors", "colors.images", "colors.variants"})
  Optional<Product> findProductDetailsById(Long id);

  @Modifying(clearAutomatically = true)
  @Query(
      "UPDATE Product p SET p.status = 'ARCHIVED', p.disabledAt = :disabledAt WHERE p.collection.id = :collectionId")
  void archiveAllByCollectionId(
      @Param("collectionId") Long collectionId, @Param("disabledAt") Instant disabledAt);

  @Modifying(clearAutomatically = true)
  @Query(
      "UPDATE Product p SET p.status = 'DRAFT', p.disabledAt = null "
          + "WHERE p.collection.id = :collectionId AND p.status = 'ARCHIVED'")
  void unarchiveAllByCollectionId(@Param("collectionId") Long collectionId);
}
