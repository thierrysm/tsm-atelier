package com.tsm.atelier.domain.collection;

import java.util.List;
import java.util.Optional;
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
    ORDER BY c.displayOrder ASC
""")
  List<Collection> findWithFilters(
      @Param("status") CollectionStatus status,
      @Param("featured") Boolean featured,
      @Param("isNew") Boolean isNew,
      @Param("showInHeader") Boolean showInHeader);

  Optional<Collection> findBySlug(String slug);

  Boolean existsByName(String name);

  Boolean existsBySlug(String slug);

  @org.springframework.data.jpa.repository.Modifying
  @Query("UPDATE Collection c SET c.showInHeader = false WHERE c.showInHeader = true")
  void unsetAllShowInHeader();

  @org.springframework.data.jpa.repository.Modifying
  @Query(
      "UPDATE Collection c SET c.showInHeader = false WHERE c.id != :id AND c.showInHeader = true")
  void unsetShowInHeaderForOthers(@Param("id") Long id);
}
