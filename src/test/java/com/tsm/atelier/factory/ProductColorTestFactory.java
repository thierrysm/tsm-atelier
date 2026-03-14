package com.tsm.atelier.factory;

import com.tsm.atelier.domain.product.Product;
import com.tsm.atelier.domain.product.ProductColor;
import com.tsm.atelier.domain.product.ProductImage;
import com.tsm.atelier.domain.product.ProductSize;
import com.tsm.atelier.domain.product.ProductVariant;
import com.tsm.atelier.domain.product.dto.v1.request.ProductColorRequestDTO;
import java.util.ArrayList;
import java.util.List;

// ProductColorTestFactory.java
public class ProductColorTestFactory {

  public static ProductColorBuilder aColor() {
    return new ProductColorBuilder();
  }

  public static ProductColorRequestDTO aColorRequest() {
    return new ProductColorRequestDTO("Areia", "#C2B280");
  }

  public static class ProductColorBuilder {
    private Long id = 1L;
    private String name = "Areia";
    private String hexCode = "#C2B280";
    private Product product = null;
    private List<ProductImage> images =
        new ArrayList<>(List.of(ProductImageTestFactory.anImage().asCover().build()));
    private List<ProductVariant> variants =
        new ArrayList<>(
            List.of(
                ProductVariantTestFactory.aVariant().withSize(ProductSize.M).withStock(5).build(),
                ProductVariantTestFactory.aVariant()
                    .withId(2L)
                    .withSize(ProductSize.G)
                    .withStock(3)
                    .build()));

    public ProductColorBuilder withId(Long id) {
      this.id = id;
      return this;
    }

    public ProductColorBuilder withName(String name) {
      this.name = name;
      return this;
    }

    public ProductColorBuilder withHexCode(String hexCode) {
      this.hexCode = hexCode;
      return this;
    }

    public ProductColorBuilder withProduct(Product product) {
      this.product = product;
      return this;
    }

    public ProductColorBuilder withImages(List<ProductImage> images) {
      this.images = images;
      return this;
    }

    public ProductColorBuilder withVariants(List<ProductVariant> variants) {
      this.variants = variants;
      return this;
    }

    public ProductColorBuilder withoutStock() {
      this.variants = List.of(ProductVariantTestFactory.aVariant().withoutStock().build());
      return this;
    }

    public ProductColor build() {
      ProductColor color = new ProductColor();
      color.setId(id);
      color.setName(name);
      color.setHexCode(hexCode);
      color.setProduct(product);
      return color;
    }
  }
}
