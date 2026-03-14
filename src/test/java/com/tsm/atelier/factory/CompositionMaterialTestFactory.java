package com.tsm.atelier.factory;

import com.tsm.atelier.domain.product.CompositionMaterial;
import com.tsm.atelier.domain.product.ProductComposition;
import com.tsm.atelier.domain.product.dto.v1.request.CompositionMaterialRequestDTO;

public class CompositionMaterialTestFactory {

  public static CompositionMaterialBuilder aCompositionMaterial() {
    return new CompositionMaterialBuilder();
  }

  public static CompositionMaterialRequestDTO aMaterialRequest() {
    return new CompositionMaterialRequestDTO("Linho", 97);
  }

  public static class CompositionMaterialBuilder {
    private Long id = 1L;
    private String name = "Linho";
    private Integer percentage = 97;
    private ProductComposition composition = null;

    public CompositionMaterialBuilder withId(Long id) {
      this.id = id;
      return this;
    }

    public CompositionMaterialBuilder withName(String name) {
      this.name = name;
      return this;
    }

    public CompositionMaterialBuilder withPercentage(Integer percentage) {
      this.percentage = percentage;
      return this;
    }

    public CompositionMaterialBuilder withComposition(ProductComposition composition) {
      this.composition = composition;
      return this;
    }

    public CompositionMaterial build() {
      CompositionMaterial material = new CompositionMaterial();
      material.setId(id);
      material.setName(name);
      material.setPercentage(percentage);
      material.setComposition(composition);
      return material;
    }
  }
}
