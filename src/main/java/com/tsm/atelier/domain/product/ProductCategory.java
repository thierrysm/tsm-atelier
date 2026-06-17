package com.tsm.atelier.domain.product;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public enum ProductCategory {
  COAT,
  JACKET,
  BLAZER,
  DRESS,
  BLOUSE,
  SHIRT,
  T_SHIRT,
  SWEATSHIRT,
  SUIT,
  JEANS,
  PANTS,
  SHORTS,
  BERMUDA;

  private static final Map<TargetAudience, Set<ProductCategory>> ALLOWED_BY_AUDIENCE;

  static {
    ALLOWED_BY_AUDIENCE = new EnumMap<>(TargetAudience.class);

    ALLOWED_BY_AUDIENCE.put(
        TargetAudience.WOMENSWEAR,
        EnumSet.of(
            COAT,
            T_SHIRT,
            JACKET,
            BLAZER,
            DRESS,
            BLOUSE,
            SWEATSHIRT,
            JEANS,
            PANTS,
            SHORTS
        ));

    ALLOWED_BY_AUDIENCE.put(
        TargetAudience.MENSWEAR,
        EnumSet.of(
            COAT,
            JACKET,
            BLAZER,
            SHIRT,
            T_SHIRT,
            SWEATSHIRT,
            JEANS,
            PANTS,
            BERMUDA,
            SUIT));

    ALLOWED_BY_AUDIENCE.put(
        TargetAudience.UNISEX,
        EnumSet.of(
            COAT,
            JACKET,
            BLAZER,
            SHIRT,
            T_SHIRT,
            SWEATSHIRT,
            JEANS,
            PANTS
        ));

  }

  public boolean isValidFor(TargetAudience audience) {
    return ALLOWED_BY_AUDIENCE
        .getOrDefault(audience, EnumSet.noneOf(ProductCategory.class))
        .contains(this);
  }
}
