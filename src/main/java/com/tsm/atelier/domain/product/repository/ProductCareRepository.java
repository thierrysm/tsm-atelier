package com.tsm.atelier.domain.product.repository;

import com.tsm.atelier.domain.product.ProductCare;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCareRepository extends JpaRepository<ProductCare, Long> {
  List<ProductCare> findAllByProductId(Long productId);
}
