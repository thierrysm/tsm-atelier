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
import com.tsm.atelier.exception.EntityAlreadyExistsException;
import com.tsm.atelier.exception.EntityNotFoundException;
import com.tsm.atelier.shared.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductColorService {

  private final ProductColorRepository productColorRepository;
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final ImageService imageService;

  @Transactional
  public ProductColorResponseDTO addColorToProduct(Long productId, ProductColorRequestDTO request) {
    Product product =
        productRepository
            .findById(productId)
            .orElseThrow(() -> new EntityNotFoundException("Produto", "id", productId));

    if (productColorRepository.existsByProductIdAndNameIgnoreCase(productId, request.name())) {
      throw new EntityAlreadyExistsException("Cor do produto", "nome", request.name());
    }
    if (request.hexCode() != null
        && productColorRepository.existsByProductIdAndHexCodeIgnoreCase(
            productId, request.hexCode())) {
      throw new EntityAlreadyExistsException("Cor do produto", "hexCode", request.hexCode());
    }

    ProductColor productColor = productMapper.toEntity(request);
    productColor.setProduct(product);
    product.getColors().add(productColor);

    productColor = productColorRepository.save(productColor);

    return productMapper.toColorResponse(productColor);
  }

  @Transactional
  public ProductColorResponseDTO updateColor(
      Long productColorId, Long productId, ProductColorPatchDTO request) {
    ProductColor productColor =
        productColorRepository
            .findByIdAndProductId(productColorId, productId)
            .orElseThrow(() -> new EntityNotFoundException("Cor do produto", "id", productColorId));

    request
        .name()
        .ifPresent(
            newName -> {
              if (productColorRepository.existsByProductIdAndNameIgnoreCaseAndIdNot(
                  productId, newName, productColorId)) {
                throw new EntityAlreadyExistsException("Cor do produto", "nome", newName);
              }
              productColor.setName(newName);
            });

    request
        .hexCode()
        .ifPresent(
            newHex -> {
              if (productColorRepository.existsByProductIdAndHexCodeIgnoreCaseAndIdNot(
                  productId, newHex, productColorId)) {
                throw new EntityAlreadyExistsException("Cor do produto", "hexCode", newHex);
              }
              productColor.setHexCode(newHex);
            });

    return productMapper.toColorResponse(productColor);
  }

  @Transactional
  public void delete(Long productColorId, Long productId) {
    ProductColor colorToDelete =
        productColorRepository
            .findByIdAndProductId(productColorId, productId)
            .orElseThrow(() -> new EntityNotFoundException("Cor do produto", "id", productColorId));

    colorToDelete.getImages().forEach(image -> imageService.deleteAfterCommit(image.getUrl()));

    Product product = colorToDelete.getProduct();
    product.getColors().remove(colorToDelete);

    if (product.getColors().isEmpty() && product.getStatus() == ProductStatus.ACTIVE) {
      product.setStatus(ProductStatus.DRAFT);
    }

    productColorRepository.delete(colorToDelete);
  }
}
