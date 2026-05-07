package com.tsm.atelier.domain.product.service;

import com.tsm.atelier.domain.product.ProductColor;
import com.tsm.atelier.domain.product.ProductImage;
import com.tsm.atelier.domain.product.dto.v1.response.ProductImageResponseDTO;
import com.tsm.atelier.domain.product.mapper.ProductMapper;
import com.tsm.atelier.domain.product.repository.ProductColorRepository;
import com.tsm.atelier.domain.product.repository.ProductImageRepository;
import com.tsm.atelier.exception.BusinessException;
import com.tsm.atelier.exception.EntityNotFoundException;
import com.tsm.atelier.shared.UploadResult;
import com.tsm.atelier.shared.image.ImageFolder;
import com.tsm.atelier.shared.image.ImageService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProductImageService {

  private final ImageService imageService;
  private final ProductColorRepository productColorRepository;
  private final ProductImageRepository productImageRepository;
  private final ProductMapper productMapper;

  @Transactional
  public List<ProductImageResponseDTO> upload(
      Long productId, Long colorId, List<MultipartFile> files, Boolean isCover) {
    if (files == null || files.isEmpty()) {
      throw new BusinessException("Nenhuma imagem enviada");
    }

    ProductColor color =
        productColorRepository
            .findByIdAndProductId(colorId, productId)
            .orElseThrow(() -> new EntityNotFoundException("Cor do produto", "id", colorId));

    if (Boolean.TRUE.equals(isCover)) {
      productImageRepository.removeCoversFromColor(colorId);
    }

    int currentOrder = productImageRepository.countByProductColorId(colorId);
    List<ProductImage> savedImages = new ArrayList<>();

    for (int i = 0; i < files.size(); i++) {
      MultipartFile file = files.get(i);
      UploadResult result = imageService.upload(file, ImageFolder.PRODUCTS);

      ProductImage image = new ProductImage();
      image.setUrl(result.url());
      image.setFileName(result.fileName());
      image.setIsCover(Boolean.TRUE.equals(isCover) && i == 0);
      currentOrder++;
      image.setDisplayOrder(currentOrder);
      image.setProductColor(color);

      savedImages.add(productImageRepository.save(image));
    }

    return savedImages.stream().map(productMapper::toImageResponse).toList();
  }

  @Transactional
  public void setCover(Long productId, Long colorId, Long imageId) {
    productColorRepository
        .findByIdAndProductId(colorId, productId)
        .orElseThrow(() -> new EntityNotFoundException("Cor do produto", "id", colorId));

    if (!productImageRepository.existsByIdAndProductColorId(imageId, colorId)) {
      throw new EntityNotFoundException("Imagem do produto", "id", imageId);
    }

    productImageRepository.clearCoversExcept(colorId, imageId);
    productImageRepository.markAsCover(imageId);
  }

  @Transactional
  public void delete(Long productId, Long colorId, Long imageId) {
    productColorRepository
        .findByIdAndProductId(colorId, productId)
        .orElseThrow(() -> new EntityNotFoundException("Cor do produto", "id", colorId));

    ProductImage image =
        productImageRepository
            .findByIdAndProductColorId(imageId, colorId)
            .orElseThrow(() -> new EntityNotFoundException("Imagem do produto", "id", imageId));

    boolean wasCover = Boolean.TRUE.equals(image.getIsCover());
    String urlToDelete = image.getUrl();

    productImageRepository.delete(image);
    imageService.deleteAfterCommit(urlToDelete);

    if (wasCover) {
      productImageRepository
          .findFirstByProductColorIdAndIdNotOrderByDisplayOrderAsc(colorId, imageId)
          .ifPresent(next -> productImageRepository.markAsCover(next.getId()));
    }
  }
}
