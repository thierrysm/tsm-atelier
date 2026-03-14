package com.tsm.atelier.domain.product;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

interface ProductRepository extends JpaRepository<Product, UUID> {

  @EntityGraph(attributePaths = {"colors", "colors.images", "colors.variants"})
  Page<Product> findAll(Pageable pageable);

  Product findByName(String name);

  boolean existsByName(String name);

  boolean existsByAlias(String alias);

  Optional<Product> findByAlias(String alias);
}
