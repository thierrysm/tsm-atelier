package com.tsm.atelier.domain.product.service;

import com.tsm.atelier.domain.product.ProductColor;
import com.tsm.atelier.domain.product.ProductImage;
import com.tsm.atelier.domain.product.dto.v1.response.ProductImageResponseDTO;
import com.tsm.atelier.domain.product.mapper.ProductMapper;
import com.tsm.atelier.domain.product.repository.ProductColorRepository;
import com.tsm.atelier.domain.product.repository.ProductImageRepository;
import com.tsm.atelier.exception.BusinessException;
import com.tsm.atelier.exception.EntityNotFoundException;
import com.tsm.atelier.shared.image.ImageFolder;
import com.tsm.atelier.shared.image.ImageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProductImageService {

  private final ImageService imageService;
  private final ProductColorRepository productColorRepository;
  private final ProductImageRepository productImageRepository;
  private final ProductMapper productMapper;

  @Transactional
  public ProductImageResponseDTO upload(
      Long productId, Long colorId, MultipartFile file, Boolean isCover) {
    ProductColor color =
        productColorRepository
            .findByIdAndProductId(colorId, productId)
            .orElseThrow(() -> new EntityNotFoundException("Cor do produto", "id", colorId));

    if (Boolean.TRUE.equals(isCover)) {
      productImageRepository.removeCoversFromColor(colorId);
    }

    String url = imageService.upload(file, ImageFolder.PRODUCTS);

    ProductImage image = new ProductImage();
    image.setUrl(url);
    image.setFileName(file.getOriginalFilename());
    image.setIsCover(isCover);
    image.setDisplayOrder(productImageRepository.countByProductColorId(colorId) + 1);
    image.setProductColor(color);

    return productMapper.toImageResponse(productImageRepository.save(image));
  }

  @Transactional
  public void setCover(Long productId, Long colorId, Long imageId) {
    ProductImage image =
        productImageRepository
            .findByIdAndProductColorId(imageId, colorId)
            .orElseThrow(() -> new EntityNotFoundException("Image do produto", "id", imageId));

    if (!image.getProductColor().getProduct().getId().equals(productId))
      throw new BusinessException("A cor do produto não pertence a este produto");

    productImageRepository.removeCoversFromColor(colorId);

    image.setIsCover(true);
  }

  @Transactional
  public void delete(Long productId, Long colorId, Long imageId) {
    ProductColor productColor =
        productColorRepository
            .findByIdAndProductId(colorId, productId)
            .orElseThrow(() -> new EntityNotFoundException("Cor do produto", "id", colorId));

    if (!productColor.getProduct().getId().equals(productId))
      throw new EntityNotFoundException("Produto", "id", productId);

    ProductImage image =
        productImageRepository
            .findById(imageId)
            .orElseThrow(() -> new EntityNotFoundException("Image do produto", "id", imageId));

    imageService.delete(image.getUrl());
    productImageRepository.delete(image);
  }
}
