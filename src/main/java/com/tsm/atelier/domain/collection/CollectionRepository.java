package com.tsm.atelier.domain.collection;

import com.tsm.atelier.domain.product.TargetAudience;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
  @Query(
"""
    SELECT c FROM Collection c
    WHERE (:status IS NULL OR c.status = :status)
    AND (:featured IS NULL OR c.featured = :featured)
    AND (:isNew IS NULL OR c.isNew = :isNew)
    AND (:showInHeader IS NULL OR c.showInHeader = :showInHeader)
    AND (:targetAudience IS NULL OR c.targetAudience = :targetAudience)
""")
  Page<Collection> findWithFilters(
      @Param("status") CollectionStatus status,
      @Param("featured") Boolean featured,
      @Param("isNew") Boolean isNew,
      @Param("showInHeader") Boolean showInHeader,
      @Param("targetAudience") TargetAudience targetAudience,
      Pageable pageable);

  Optional<Collection> findBySlug(String slug);

  Boolean existsByName(String name);

  Boolean existsBySlug(String slug);

  @org.springframework.data.jpa.repository.Modifying
  @Query(
      "UPDATE Collection c SET c.showInHeader = false WHERE c.targetAudience = :targetAudience AND c.showInHeader = true")
  void unsetAllShowInHeaderForTargetAudience(
      @Param("targetAudience") TargetAudience targetAudience);

  @org.springframework.data.jpa.repository.Modifying
  @Query(
      "UPDATE Collection c SET c.showInHeader = false WHERE c.id != :id AND c.targetAudience = :targetAudience AND c.showInHeader = true")
  void unsetShowInHeaderForOthersInTargetAudience(
      @Param("id") Long id, @Param("targetAudience") TargetAudience targetAudience);
}
