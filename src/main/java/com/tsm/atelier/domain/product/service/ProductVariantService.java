package com.tsm.atelier.domain.product.service;

import com.tsm.atelier.domain.product.Product;
import com.tsm.atelier.domain.product.ProductColor;
import com.tsm.atelier.domain.product.ProductStatus;
import com.tsm.atelier.domain.product.ProductVariant;
import com.tsm.atelier.domain.product.dto.v1.request.ProductVariantPatchDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductVariantRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductVariantResponseDTO;
import com.tsm.atelier.domain.product.mapper.ProductMapper;
import com.tsm.atelier.domain.product.repository.ProductColorRepository;
import com.tsm.atelier.domain.product.repository.ProductVariantRepository;
import com.tsm.atelier.exception.BusinessException;
import com.tsm.atelier.exception.EntityAlreadyExistsException;
import com.tsm.atelier.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductVariantService {

  private final ProductVariantRepository productVariantRepository;
  private final ProductColorRepository productColorRepository;
  private final ProductMapper productMapper;

  @Transactional
  public ProductVariantResponseDTO addVariant(
      Long productId, Long colorId, ProductVariantRequestDTO request) {
    if (productVariantRepository.existsByProductColorIdAndSize(colorId, request.size())) {
      throw new EntityAlreadyExistsException(
          "Variante do produto", "tamanho", request.size().toString());
    }

    ProductColor productColor =
        productColorRepository
            .findByIdAndProductId(colorId, productId)
            .orElseThrow(() -> new EntityNotFoundException("Cor do produto", "id", colorId));

    ProductVariant variant = productMapper.toEntity(request);
    variant.setProductColor(productColor);

    return productMapper.toVariantResponse(productVariantRepository.save(variant));
  }

  @Transactional
  public ProductVariantResponseDTO updateStock(
      Long productId, Long colorId, Long variantId, ProductVariantPatchDTO request) {
    ProductVariant variant =
        productVariantRepository
            .findByIdAndProductColorIdAndProductColorProductId(variantId, colorId, productId)
            .orElseThrow(() -> new EntityNotFoundException("Variante de produto", "id", variantId));

    request.stock().ifPresent(variant::setStock);

    return productMapper.toVariantResponse(productVariantRepository.save(variant));
  }

  @Transactional
  public void delete(Long productId, Long productColorId, Long variantId) {

    ProductVariant variantToDelete =
        productVariantRepository
            .findById(variantId)
            .orElseThrow(() -> new EntityNotFoundException("Variante do produto", "id", variantId));

    ProductColor parentColor = variantToDelete.getProductColor();
    Product parentProduct = parentColor.getProduct();

    if (!parentColor.getId().equals(productColorId) || !parentProduct.getId().equals(productId)) {
      throw new BusinessException(
          "A variante informada não pertence à cor ou ao produto especificado na URL.");
    }

    parentColor.getVariants().removeIf(variant -> variant.getId().equals(variantId));

    if (parentColor.getVariants().isEmpty()) {
      parentProduct.setStatus(ProductStatus.DRAFT);
    }
  }
}
