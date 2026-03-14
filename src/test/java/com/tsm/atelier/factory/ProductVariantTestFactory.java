package com.tsm.atelier.factory;

import com.tsm.atelier.domain.product.ProductColor;
import com.tsm.atelier.domain.product.ProductSize;
import com.tsm.atelier.domain.product.ProductVariant;
import com.tsm.atelier.domain.product.dto.v1.request.ProductVariantRequestDTO;

public class ProductVariantTestFactory {

  public static ProductVariantBuilder aVariant() {
    return new ProductVariantBuilder();
  }

  public static ProductVariantRequestDTO aVariantRequest() {
    return new ProductVariantRequestDTO(ProductSize.M, 5);
  }

  public static class ProductVariantBuilder {
    private Long id = 1L;
    private ProductSize size = ProductSize.M;
    private Integer stock = 5;
    private Integer version = 0;
    private ProductColor productColor = null;

    public ProductVariantBuilder withId(Long id) {
      this.id = id;
      return this;
    }

    public ProductVariantBuilder withSize(ProductSize size) {
      this.size = size;
      return this;
    }

    public ProductVariantBuilder withStock(Integer stock) {
      this.stock = stock;
      return this;
    }

    public ProductVariantBuilder withoutStock() {
      this.stock = 0;
      return this;
    }

    public ProductVariantBuilder withProductColor(ProductColor productColor) {
      this.productColor = productColor;
      return this;
    }

    public ProductVariant build() {
      ProductVariant variant = new ProductVariant();
      variant.setId(id);
      variant.setSize(size);
      variant.setStock(stock);
      variant.setVersion(version);
      variant.setProductColor(productColor);
      return variant;
    }
  }
}
