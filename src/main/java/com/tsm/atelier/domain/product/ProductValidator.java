package com.tsm.atelier.domain.product;

import com.tsm.atelier.domain.product.dto.v1.internal.ProductIntegrityDTO;
import com.tsm.atelier.domain.product.dto.v1.request.CompositionMaterialRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductCompositionRequestDTO;
import com.tsm.atelier.exception.BusinessException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {

  public void validateForPublication(ProductIntegrityDTO integrity) {
    List<String> errors = new ArrayList<>();

    if (integrity.colorCount() == 0) {
      errors.add("O produto deve ter pelo menos uma cor cadastrada.");
    }

    if (integrity.variantCount() == 0) {
      errors.add("O produto deve ter pelo menos um tamanho/variante cadastrado.");
    }

    if (integrity.imageCount() == 0) {
      errors.add("O produto deve ter pelo menos uma imagem cadastrada.");
    }

    if (integrity.colorCount() > 0 && integrity.colorCount() != integrity.colorsWithCoverImage()) {
      errors.add("Todas as cores do produto devem possuir uma imagem de capa.");
    }

    if (!errors.isEmpty()) {
      throw new BusinessException(errors);
    }
  }

  public void validateCompositions(List<ProductCompositionRequestDTO> compositions) {
    List<String> errors = new ArrayList<>();
    Set<CompositionType> seenTypes = new HashSet<>();

    for (ProductCompositionRequestDTO composition : compositions) {
      if (!seenTypes.add(composition.type())) {
        errors.add("Composição duplicada para o tipo: " + composition.type());
      }

      int total =
          composition.materials().stream()
              .mapToInt(CompositionMaterialRequestDTO::percentage)
              .sum();
      if (total != 100) {
        errors.add(
            "A soma das porcentagens dos materiais da composição "
                + composition.type()
                + " deve ser 100%. Atual: "
                + total
                + "%");
      }
    }

    if (!errors.isEmpty()) {
      throw new BusinessException(errors);
    }
  }
}
