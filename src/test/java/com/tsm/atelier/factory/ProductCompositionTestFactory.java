package com.tsm.atelier.factory;

import com.tsm.atelier.domain.product.CompositionMaterial;
import com.tsm.atelier.domain.product.CompositionType;
import com.tsm.atelier.domain.product.Product;
import com.tsm.atelier.domain.product.ProductComposition;
import com.tsm.atelier.domain.product.dto.v1.request.CompositionMaterialRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductCompositionRequestDTO;
import java.util.ArrayList;
import java.util.List;

public class ProductCompositionTestFactory {

  public static ProductCompositionBuilder aProductComposition() {
    return new ProductCompositionBuilder();
  }

  public static ProductCompositionRequestDTO aProductCompositionRequest() {
    return new ProductCompositionRequestDTO(
        CompositionType.EXTERNAL,
        List.of(
            CompositionMaterialTestFactory.aMaterialRequest(),
            new CompositionMaterialRequestDTO("Elastano", 3)));
  }

  public static class ProductCompositionBuilder {
    private Long id = 1L;
    private CompositionType type = CompositionType.EXTERNAL;
    private Product product = null;
    private List<CompositionMaterial> materials =
        new ArrayList<>(
            List.of(
                CompositionMaterialTestFactory.aCompositionMaterial().build(),
                CompositionMaterialTestFactory.aCompositionMaterial()
                    .withId(2L)
                    .withName("Elastano")
                    .withPercentage(3)
                    .build()));

    public ProductCompositionBuilder withId(Long id) {
      this.id = id;
      return this;
    }

    public ProductCompositionBuilder withType(CompositionType type) {
      this.type = type;
      return this;
    }

    public ProductCompositionBuilder withProduct(Product product) {
      this.product = product;
      return this;
    }

    public ProductCompositionBuilder withMaterials(List<CompositionMaterial> materials) {
      this.materials = materials;
      return this;
    }

    public ProductComposition build() {
      ProductComposition composition = new ProductComposition();
      composition.setId(id);
      composition.setType(type);
      composition.setProduct(product);
      composition.setMaterials(materials);
      return composition;
    }
  }
}
