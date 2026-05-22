package com.tsm.atelier.domain.product.dto.v1.request;

import com.tsm.atelier.domain.product.ProductCategory;
import com.tsm.atelier.domain.product.TargetAudience;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public record ProductRequestDTO(
    @NotBlank @Size(min = 3, max = 150) String name,
    @NotBlank @Size(min = 5) String description,
    @NotNull @DecimalMin(value = "0.01") BigDecimal price,
    @DecimalMin(value = "0.01") BigDecimal promotionalPrice,
    @NotNull ProductCategory category,
    @NotNull TargetAudience targetAudience,
    Long collectionId,
    @NotNull @Valid List<ProductCompositionRequestDTO> compositions,
    @NotEmpty @Valid List<String> careInstructions) {}
