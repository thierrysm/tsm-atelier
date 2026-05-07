package com.tsm.atelier.factory;

import com.tsm.atelier.domain.product.CompositionMaterial;
import com.tsm.atelier.domain.product.dto.v1.request.CompositionMaterialRequestDTO;

public class CompositionMaterialTestFactory {

  public static CompositionMaterialBuilder aCompositionMaterial() {
    return new CompositionMaterialBuilder();
  }

  public static CompositionMaterialRequestDTO aMaterialRequest() {
    return new CompositionMaterialRequestDTO("Linho", 97);
  }

  public static class CompositionMaterialBuilder {
    private String name = "Linho";
    private Integer percentage = 93;

    public CompositionMaterialBuilder withName(String name) {
      this.name = name;
      return this;
    }

    public CompositionMaterialBuilder withPercentage(Integer percentage) {
      this.percentage = percentage;
      return this;
    }

    public CompositionMaterial build() {
      String name = this.name;
      Integer percentage = this.percentage;
      return new CompositionMaterial(name, percentage);
    }
  }
}
