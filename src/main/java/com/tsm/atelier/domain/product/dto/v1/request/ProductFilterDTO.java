package com.tsm.atelier.domain.product.dto.v1.request;

import com.tsm.atelier.domain.product.ProductCategory;
import com.tsm.atelier.domain.product.ProductSize;
import com.tsm.atelier.domain.product.TargetAudience;
import java.math.BigDecimal;

public record ProductFilterDTO(
    String query,
    ProductCategory category,
    TargetAudience targetAudience,
    Long collectionId,
    ProductSize productSize,
    BigDecimal minPrice,
    BigDecimal maxPrice,
    Boolean isOnSale) {}
