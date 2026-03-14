package com.tsm.atelier.factory;

import com.tsm.atelier.domain.product.ProductColor;
import com.tsm.atelier.domain.product.ProductImage;

public class ProductImageTestFactory {

  public static ProductImageBuilder anImage() {
    return new ProductImageBuilder();
  }

  public static class ProductImageBuilder {
    private Long id = 1L;
    private String url = "/images/vestido-linho-areia-1.jpg";
    private String fileName = "vestido-linho-areia-1.jpg";
    private Integer displayOrder = 1;
    private Boolean isCover = true;
    private ProductColor productColor = null;

    public ProductImageBuilder withId(Long id) {
      this.id = id;
      return this;
    }

    public ProductImageBuilder withUrl(String url) {
      this.url = url;
      return this;
    }

    public ProductImageBuilder withDisplayOrder(Integer displayOrder) {
      this.displayOrder = displayOrder;
      return this;
    }

    public ProductImageBuilder asCover() {
      this.isCover = true;
      return this;
    }

    public ProductImageBuilder notCover() {
      this.isCover = false;
      return this;
    }

    public ProductImageBuilder withProductColor(ProductColor productColor) {
      this.productColor = productColor;
      return this;
    }

    public ProductImage build() {
      ProductImage image = new ProductImage();
      image.setId(id);
      image.setUrl(url);
      image.setFileName(fileName);
      image.setDisplayOrder(displayOrder);
      image.setIsCover(isCover);
      image.setProductColor(productColor);
      return image;
    }
  }
}
