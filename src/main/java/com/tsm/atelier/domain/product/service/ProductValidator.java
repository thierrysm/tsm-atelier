package com.tsm.atelier.domain.product.service;

import com.tsm.atelier.domain.product.dto.v1.internal.ProductIntegrityDTO;
import com.tsm.atelier.exception.BusinessException;
import java.util.ArrayList;
import java.util.List;
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

    if (integrity.coverImageCount() == 0) {
      errors.add("O produto deve ter uma imagem marcada como capa.");
    }

    if (integrity.colorCount() != integrity.colorsWithCoverImage()) {
      errors.add("Todas as cores do produto devem possuir pelo menos uma imagem de capa.");
    }

    if (!errors.isEmpty()) {
      throw new BusinessException(String.join(", ", errors));
    }
  }
}
