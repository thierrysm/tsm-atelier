package com.tsm.atelier.factory;

import com.tsm.atelier.domain.product.Product;
import com.tsm.atelier.domain.product.ProductCare;
import com.tsm.atelier.domain.product.dto.v1.request.ProductCareRequestDTO;

public class ProductCareTestFactory {

  public static ProductCareBuilder aCare() {
    return new ProductCareBuilder();
  }

  public static ProductCareRequestDTO aCareRequest() {
    return new ProductCareRequestDTO("Lavar à mão com água fria", 1);
  }

  public static class ProductCareBuilder {
    private Long id = 1L;
    private String instruction = "Lavar à mão com água fria";
    private Integer displayOrder = 1;
    private Product product = null;

    public ProductCareBuilder withId(Long id) {
      this.id = id;
      return this;
    }

    public ProductCareBuilder withInstruction(String instruction) {
      this.instruction = instruction;
      return this;
    }

    public ProductCareBuilder withDisplayOrder(Integer displayOrder) {
      this.displayOrder = displayOrder;
      return this;
    }

    public ProductCareBuilder withProduct(Product product) {
      this.product = product;
      return this;
    }

    public ProductCare build() {
      ProductCare care = new ProductCare();
      care.setId(id);
      care.setInstruction(instruction);
      care.setDisplayOrder(displayOrder);
      care.setProduct(product);
      return care;
    }
  }
}
