package com.tsm.atelier.domain.product;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public enum ProductCategory {
  CASACOS,
  JAQUETAS,
  BLAZERS_COLETES,
  VESTIDOS,
  MACACOES,
  BLUSAS_BODIES,
  MALHAS,
  CAMISAS,
  CAMISETAS,
  MOLETOM,
  JEANS,
  CALCAS,
  SHORTS;

  private static final Map<TargetAudience, Set<ProductCategory>> ALLOWED_BY_AUDIENCE;

  static {
    ALLOWED_BY_AUDIENCE = new EnumMap<>(TargetAudience.class);

    ALLOWED_BY_AUDIENCE.put(
        TargetAudience.WOMENSWEAR,
        EnumSet.of(
            CASACOS,
            JAQUETAS,
            BLAZERS_COLETES,
            VESTIDOS,
            MACACOES,
            BLUSAS_BODIES,
            MALHAS,
            CAMISAS,
            CAMISETAS,
            MOLETOM,
            JEANS,
            CALCAS,
            SHORTS));

    ALLOWED_BY_AUDIENCE.put(
        TargetAudience.MENSWEAR,
        EnumSet.of(
            CASACOS,
            JAQUETAS,
            BLAZERS_COLETES,
            MALHAS,
            CAMISAS,
            CAMISETAS,
            MOLETOM,
            JEANS,
            CALCAS,
            SHORTS));

    ALLOWED_BY_AUDIENCE.put(
        TargetAudience.UNISEX,
        EnumSet.of(
            CASACOS,
            JAQUETAS,
            BLAZERS_COLETES,
            MALHAS,
            CAMISAS,
            CAMISETAS,
            MOLETOM,
            JEANS,
            CALCAS,
            SHORTS));

    ALLOWED_BY_AUDIENCE.put(
        TargetAudience.KIDS, EnumSet.of(CAMISETAS, MOLETOM, JEANS, CALCAS, SHORTS));
  }

  public boolean isValidFor(TargetAudience audience) {
    return ALLOWED_BY_AUDIENCE
        .getOrDefault(audience, EnumSet.noneOf(ProductCategory.class))
        .contains(this);
  }
}
