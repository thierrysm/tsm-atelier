package com.tsm.atelier.domain.product.dto.v1.response;

import com.tsm.atelier.domain.product.ProductCategory;
import com.tsm.atelier.domain.product.ProductStatus;
import com.tsm.atelier.domain.product.TargetAudience;
import java.math.BigDecimal;
import java.util.List;

public record ProductDetailsResponseDTO(
    Long id,
    String name,
    String slug,
    String description,
    BigDecimal price,
    BigDecimal promotionalPrice,
    ProductStatus status,
    List<ProductCompositionResponseDTO> productComposition,
    ProductCategory productCategory,
    TargetAudience targetAudience,
    String collectionName,
    List<String> careInstructions,
    List<ProductColorDetailsResponseDTO> colors) {}
