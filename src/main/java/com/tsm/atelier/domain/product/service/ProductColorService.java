package com.tsm.atelier.domain.product.service;

import com.tsm.atelier.domain.product.Product;
import com.tsm.atelier.domain.product.ProductColor;
import com.tsm.atelier.domain.product.ProductStatus;
import com.tsm.atelier.domain.product.dto.v1.request.ProductColorPatchDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductColorRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductColorResponseDTO;
import com.tsm.atelier.domain.product.mapper.ProductMapper;
import com.tsm.atelier.domain.product.repository.ProductColorRepository;
import com.tsm.atelier.domain.product.repository.ProductRepository;
import com.tsm.atelier.exception.BusinessException;
import com.tsm.atelier.exception.EntityAlreadyExistsException;
import com.tsm.atelier.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductColorService {

  private final ProductColorRepository productColorRepository;
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  @Transactional
  public ProductColorResponseDTO addColorToProduct(Long productId, ProductColorRequestDTO request) {
    Product product =
        productRepository
            .findById(productId)
            .orElseThrow(() -> new EntityNotFoundException("Produto", "id", productId));

    boolean colorExists =
        product.getColors().stream()
            .anyMatch(
                existingColor ->
                    existingColor.getName().equalsIgnoreCase(request.name())
                        || existingColor.getHexCode().equalsIgnoreCase(request.hexCode()));

    if (colorExists) {
      throw new EntityAlreadyExistsException(
          "Cor do produto", "nome/hexCode", request.name() + " ou " + request.hexCode());
    }

    ProductColor productColor = productMapper.toEntity(request);
    productColor.setProduct(product);

    productColor = productColorRepository.save(productColor);

    return productMapper.toColorResponse(productColor);
  }

  @Transactional
  public ProductColorResponseDTO updateColor(
      Long productColorId, Long productId, ProductColorPatchDTO request) {
    ProductColor productColor =
        productColorRepository
            .findById(productColorId)
            .orElseThrow(() -> new EntityNotFoundException("Cor do produto", "id", productColorId));

    if (!productColor.getProduct().getId().equals(productId)) {
      throw new BusinessException("A cor informada não pertence a este produto");
    }

    if (request.name().isPresent() || request.hexCode().isPresent()) {

      String nameToCheck = request.name().orElse(productColor.getName());
      String hexToCheck = request.hexCode().orElse(productColor.getHexCode());

      boolean isDuplicate =
          productColor.getProduct().getColors().stream()
              .anyMatch(
                  existingColor ->
                      !existingColor.getId().equals(productColorId)
                          && (existingColor.getName().equalsIgnoreCase(nameToCheck)
                              || existingColor.getHexCode().equalsIgnoreCase(hexToCheck)));

      if (isDuplicate) {
        throw new EntityAlreadyExistsException(
            "Já existe outra cor com este nome ou código hexadecimal vinculada a este produto.");
      }
    }

    request.name().ifPresent(productColor::setName);
    request.hexCode().ifPresent(productColor::setHexCode);

    return productMapper.toColorResponse(productColor);
  }

  @Transactional
  public void delete(Long productColorId, Long productId) {
    Product product =
        productRepository
            .findById(productId)
            .orElseThrow(() -> new EntityNotFoundException("Produto", "id", productId));

    ProductColor colorToDelete =
        productColorRepository
            .findById(productColorId)
            .orElseThrow(() -> new EntityNotFoundException("Cor do produto", "id", productColorId));

    if (!colorToDelete.getProduct().getId().equals(productId)) {
      throw new BusinessException("A cor informada não pertence a este produto");
    }

    product.getColors().remove(colorToDelete);

    if (product.getColors().isEmpty()) {
      product.setStatus(ProductStatus.DRAFT);
    }
  }
}
