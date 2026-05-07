package com.tsm.atelier.domain.product.dto.v1.request;

import com.tsm.atelier.domain.product.ProductCategory;
import com.tsm.atelier.domain.product.ProductSize;
import java.math.BigDecimal;

public record ProductFilterDTO(
    String query,
    ProductCategory category,
    Long collectionId,
    ProductSize productSize,
    BigDecimal minPrice,
    BigDecimal maxPrice) {}
