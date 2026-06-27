package com.tsm.atelier.domain.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tsm.atelier.domain.product.Product;
import com.tsm.atelier.domain.product.ProductColor;
import com.tsm.atelier.domain.product.ProductImage;
import com.tsm.atelier.domain.product.dto.v1.response.ProductImageResponseDTO;
import com.tsm.atelier.domain.product.mapper.ProductMapper;
import com.tsm.atelier.domain.product.repository.ProductColorRepository;
import com.tsm.atelier.domain.product.repository.ProductImageRepository;
import com.tsm.atelier.exception.BusinessException;
import com.tsm.atelier.exception.EntityNotFoundException;
import com.tsm.atelier.factory.ProductColorTestFactory;
import com.tsm.atelier.factory.ProductImageTestFactory;
import com.tsm.atelier.factory.ProductTestFactory;
import com.tsm.atelier.shared.UploadResult;
import com.tsm.atelier.shared.image.ImageFolder;
import com.tsm.atelier.shared.image.ImageService;
import java.util.Optional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

@ExtendWith(MockitoExtension.class)
class ProductImageServiceTest {

  @Mock private ImageService imageService;
  @Mock private ProductColorRepository productColorRepository;
  @Mock private ProductImageRepository productImageRepository;
  @Mock private ProductMapper productMapper;

  @InjectMocks private ProductImageService productImageService;

  @Nested
  @DisplayName("Tests for upload")
  class UploadTests {

    @Test
    @DisplayName("Should upload image successfully as cover")
    void shouldUploadImageSuccessfullyAsCover() {
      Long productId = 1L;
      Long colorId = 1L;
      Boolean isCover = true;
      MockMultipartFile file =
          new MockMultipartFile("file", "test.jpg", "image/jpeg", "test data".getBytes());
      ProductColor color = ProductColorTestFactory.aColor().withId(colorId).build();
      UploadResult uploadResult = new UploadResult("http://url.com/test.jpg", "test.jpg");

      ProductImage savedImage = new ProductImage();
      savedImage.setUrl(uploadResult.url());
      savedImage.setFileName(file.getOriginalFilename());
      savedImage.setIsCover(isCover);
      savedImage.setDisplayOrder(2);
      savedImage.setProductColor(color);

      ProductImageResponseDTO response =
          new ProductImageResponseDTO(1L, "http://url.com/test.jpg", "test.jpg", 2, true);

      when(productColorRepository.findByIdAndProductId(colorId, productId))
          .thenReturn(Optional.of(color));
      when(imageService.upload(file, ImageFolder.PRODUCTS)).thenReturn(uploadResult);
      when(productImageRepository.countByProductColorId(colorId)).thenReturn(1);
      when(productImageRepository.save(any(ProductImage.class))).thenReturn(savedImage);
      when(productMapper.toImageResponse(any(ProductImage.class))).thenReturn(response);

      java.util.List<ProductImageResponseDTO> result =
          productImageService.upload(productId, colorId, java.util.List.of(file), isCover);

      assertEquals(java.util.List.of(response), result);
      verify(productImageRepository).removeCoversFromColor(colorId);
      verify(productImageRepository).save(any(ProductImage.class));
      verify(imageService).upload(file, ImageFolder.PRODUCTS);
    }

    @Test
    @DisplayName("Should upload image successfully not as cover")
    void shouldUploadImageSuccessfullyNotAsCover() {
      Long productId = 1L;
      Long colorId = 1L;
      Boolean isCover = false;
      MockMultipartFile file =
          new MockMultipartFile("file", "test.jpg", "image/jpeg", "test data".getBytes());
      ProductColor color = ProductColorTestFactory.aColor().withId(colorId).build();
      UploadResult uploadResult = new UploadResult("http://url.com/test.jpg", "test.jpg");

      ProductImage savedImage = new ProductImage();
      ProductImageResponseDTO response =
          new ProductImageResponseDTO(1L, "http://url.com/test.jpg", "test.jpg", 2, false);

      when(productColorRepository.findByIdAndProductId(colorId, productId))
          .thenReturn(Optional.of(color));
      when(imageService.upload(file, ImageFolder.PRODUCTS)).thenReturn(uploadResult);
      when(productImageRepository.countByProductColorId(colorId)).thenReturn(1);
      when(productImageRepository.save(any(ProductImage.class))).thenReturn(savedImage);
      when(productMapper.toImageResponse(any(ProductImage.class))).thenReturn(response);

      java.util.List<ProductImageResponseDTO> result =
          productImageService.upload(productId, colorId, java.util.List.of(file), isCover);

      assertEquals(java.util.List.of(response), result);
      verify(productImageRepository, never()).removeCoversFromColor(colorId);
      verify(productImageRepository).save(any(ProductImage.class));
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when product color not found")
    void shouldThrowExceptionWhenColorNotFound() {
      Long productId = 1L;
      Long colorId = 1L;
      MockMultipartFile file =
          new MockMultipartFile("file", "test.jpg", "image/jpeg", "test data".getBytes());

      when(productColorRepository.findByIdAndProductId(colorId, productId))
          .thenReturn(Optional.empty());

      assertThrows(
          EntityNotFoundException.class,
          () -> productImageService.upload(productId, colorId, java.util.List.of(file), true));
      verify(imageService, never()).upload(any(), any());
      verify(productImageRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should throw BusinessException and not clear covers when files list is empty")
    void shouldThrowBusinessExceptionWhenFilesEmpty() {
      Long productId = 1L;
      Long colorId = 1L;

      assertThrows(
          BusinessException.class,
          () -> productImageService.upload(productId, colorId, java.util.List.of(), true));
      verify(productImageRepository, never()).removeCoversFromColor(any());
      verify(productColorRepository, never()).findByIdAndProductId(any(), any());
      verify(imageService, never()).upload(any(), any());
    }
  }

  @Nested
  @DisplayName("Tests for setCover")
  class SetCoverTests {

    @Test
    @DisplayName("Should set image as cover successfully")
    void shouldSetImageAsCoverSuccessfully() {
      Long productId = 1L;
      Long colorId = 1L;
      Long imageId = 1L;

      Product product = ProductTestFactory.aProduct().withId(productId).build();
      ProductColor color =
          ProductColorTestFactory.aColor().withId(colorId).withProduct(product).build();

      when(productColorRepository.findByIdAndProductId(colorId, productId))
          .thenReturn(Optional.of(color));
      when(productImageRepository.existsByIdAndProductColorId(imageId, colorId)).thenReturn(true);

      productImageService.setCover(productId, colorId, imageId);

      verify(productImageRepository).clearCoversExcept(colorId, imageId);
      verify(productImageRepository).markAsCover(imageId);
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when color not found")
    void shouldThrowExceptionWhenColorNotFound() {
      Long productId = 1L;
      Long colorId = 1L;
      Long imageId = 1L;

      when(productColorRepository.findByIdAndProductId(colorId, productId))
          .thenReturn(Optional.empty());

      assertThrows(
          EntityNotFoundException.class,
          () -> productImageService.setCover(productId, colorId, imageId));
      verify(productImageRepository, never()).existsByIdAndProductColorId(any(), any());
      verify(productImageRepository, never()).clearCoversExcept(any(), any());
      verify(productImageRepository, never()).markAsCover(any());
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when image not found")
    void shouldThrowExceptionWhenImageNotFound() {
      Long productId = 1L;
      Long colorId = 1L;
      Long imageId = 1L;

      Product product = ProductTestFactory.aProduct().withId(productId).build();
      ProductColor color =
          ProductColorTestFactory.aColor().withId(colorId).withProduct(product).build();

      when(productColorRepository.findByIdAndProductId(colorId, productId))
          .thenReturn(Optional.of(color));
      when(productImageRepository.existsByIdAndProductColorId(imageId, colorId)).thenReturn(false);

      assertThrows(
          EntityNotFoundException.class,
          () -> productImageService.setCover(productId, colorId, imageId));
      verify(productImageRepository, never()).clearCoversExcept(any(), any());
      verify(productImageRepository, never()).markAsCover(any());
    }
  }

  @Nested
  @DisplayName("Tests for delete")
  class DeleteTests {

    @Test
    @DisplayName("Should delete non-cover image without promoting another")
    void shouldDeleteNonCoverImageSuccessfully() {
      Long productId = 1L;
      Long colorId = 1L;
      Long imageId = 1L;

      ProductColor color = ProductColorTestFactory.aColor().withId(colorId).build();
      ProductImage image =
          ProductImageTestFactory.anImage()
              .withId(imageId)
              .withUrl("http://url.com/test.jpg")
              .withProductColor(color)
              .notCover()
              .build();

      when(productColorRepository.findByIdAndProductId(colorId, productId))
          .thenReturn(Optional.of(color));
      when(productImageRepository.findByIdAndProductColorId(imageId, colorId))
          .thenReturn(Optional.of(image));

      productImageService.delete(productId, colorId, imageId);

      verify(productImageRepository).delete(image);
      verify(imageService).deleteAfterCommit(image.getUrl());
      verify(productImageRepository, never())
          .findFirstByProductColorIdAndIdNotOrderByDisplayOrderAsc(any(), any());
      verify(productImageRepository, never()).markAsCover(any());
    }

    @Test
    @DisplayName("Should promote next image to cover when deleting the current cover")
    void shouldPromoteNextImageWhenDeletingCover() {
      Long productId = 1L;
      Long colorId = 1L;
      Long imageId = 1L;
      Long nextImageId = 2L;

      ProductColor color = ProductColorTestFactory.aColor().withId(colorId).build();
      ProductImage cover =
          ProductImageTestFactory.anImage()
              .withId(imageId)
              .withUrl("http://url.com/cover.jpg")
              .withProductColor(color)
              .asCover()
              .build();
      ProductImage next =
          ProductImageTestFactory.anImage()
              .withId(nextImageId)
              .withProductColor(color)
              .notCover()
              .build();

      when(productColorRepository.findByIdAndProductId(colorId, productId))
          .thenReturn(Optional.of(color));
      when(productImageRepository.findByIdAndProductColorId(imageId, colorId))
          .thenReturn(Optional.of(cover));
      when(productImageRepository.findFirstByProductColorIdAndIdNotOrderByDisplayOrderAsc(
              colorId, imageId))
          .thenReturn(Optional.of(next));

      productImageService.delete(productId, colorId, imageId);

      verify(productImageRepository).delete(cover);
      verify(imageService).deleteAfterCommit(cover.getUrl());
      verify(productImageRepository).markAsCover(nextImageId);
    }

    @Test
    @DisplayName("Should not call markAsCover when deleting the last (cover) image")
    void shouldNotPromoteWhenDeletingLastCoverImage() {
      Long productId = 1L;
      Long colorId = 1L;
      Long imageId = 1L;

      ProductColor color = ProductColorTestFactory.aColor().withId(colorId).build();
      ProductImage cover =
          ProductImageTestFactory.anImage()
              .withId(imageId)
              .withUrl("http://url.com/cover.jpg")
              .withProductColor(color)
              .asCover()
              .build();

      when(productColorRepository.findByIdAndProductId(colorId, productId))
          .thenReturn(Optional.of(color));
      when(productImageRepository.findByIdAndProductColorId(imageId, colorId))
          .thenReturn(Optional.of(cover));
      when(productImageRepository.findFirstByProductColorIdAndIdNotOrderByDisplayOrderAsc(
              colorId, imageId))
          .thenReturn(Optional.empty());

      productImageService.delete(productId, colorId, imageId);

      verify(productImageRepository).delete(cover);
      verify(imageService).deleteAfterCommit(cover.getUrl());
      verify(productImageRepository, never()).markAsCover(any());
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when color not found")
    void shouldThrowExceptionWhenColorNotFound() {
      Long productId = 1L;
      Long colorId = 1L;
      Long imageId = 1L;

      when(productColorRepository.findByIdAndProductId(colorId, productId))
          .thenReturn(Optional.empty());

      assertThrows(
          EntityNotFoundException.class,
          () -> productImageService.delete(productId, colorId, imageId));
      verify(imageService, never()).deleteAfterCommit(any());
      verify(productImageRepository, never()).delete(any());
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when image not found")
    void shouldThrowExceptionWhenImageNotFound() {
      Long productId = 1L;
      Long colorId = 1L;
      Long imageId = 1L;

      ProductColor color = ProductColorTestFactory.aColor().withId(colorId).build();

      when(productColorRepository.findByIdAndProductId(colorId, productId))
          .thenReturn(Optional.of(color));
      when(productImageRepository.findByIdAndProductColorId(imageId, colorId))
          .thenReturn(Optional.empty());

      assertThrows(
          EntityNotFoundException.class,
          () -> productImageService.delete(productId, colorId, imageId));
      verify(imageService, never()).deleteAfterCommit(any());
      verify(productImageRepository, never()).delete(any());
    }
  }
}
