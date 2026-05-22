package com.tsm.atelier.domain.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tsm.atelier.domain.product.Product;
import com.tsm.atelier.domain.product.ProductColor;
import com.tsm.atelier.domain.product.ProductSize;
import com.tsm.atelier.domain.product.ProductStatus;
import com.tsm.atelier.domain.product.ProductVariant;
import com.tsm.atelier.domain.product.dto.v1.request.ProductVariantPatchDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductVariantRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductVariantResponseDTO;
import com.tsm.atelier.domain.product.mapper.ProductMapper;
import com.tsm.atelier.domain.product.repository.ProductColorRepository;
import com.tsm.atelier.domain.product.repository.ProductVariantRepository;
import com.tsm.atelier.exception.EntityAlreadyExistsException;
import com.tsm.atelier.exception.EntityNotFoundException;
import com.tsm.atelier.factory.ProductColorTestFactory;
import com.tsm.atelier.factory.ProductTestFactory;
import com.tsm.atelier.factory.ProductVariantTestFactory;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Disabled
@ExtendWith(MockitoExtension.class)
class ProductVariantServiceTest {

  @Mock private ProductVariantRepository productVariantRepository;
  @Mock private ProductColorRepository productColorRepository;
  @Mock private ProductMapper productMapper;

  @InjectMocks private ProductVariantService productVariantService;

  @Nested
  @DisplayName("Tests for addVariant")
  class AddVariantTests {

    @Test
    @DisplayName("Should add variant successfully")
    void shouldAddVariantSuccessfully() {
      Long productId = 1L;
      Long colorId = 1L;
      ProductVariantRequestDTO request = new ProductVariantRequestDTO(ProductSize.M, 10);
      ProductColor color = ProductColorTestFactory.aColor().withId(colorId).build();
      ProductVariant variant =
          ProductVariantTestFactory.aVariant().withSize(ProductSize.M).withStock(10).build();
      ProductVariantResponseDTO response = new ProductVariantResponseDTO(1L, ProductSize.M, 10);

      when(productVariantRepository.existsByProductColorIdAndSize(colorId, request.size()))
          .thenReturn(false);
      when(productColorRepository.findByIdAndProductId(colorId, productId))
          .thenReturn(Optional.of(color));
      when(productMapper.toEntity(request)).thenReturn(variant);
      when(productVariantRepository.save(variant)).thenReturn(variant);
      when(productMapper.toVariantResponse(variant)).thenReturn(response);

      ProductVariantResponseDTO result =
          productVariantService.addVariant(productId, colorId, request);

      assertEquals(response, result);
      assertEquals(color, variant.getProductColor());
      verify(productVariantRepository).save(variant);
    }

    @Test
    @DisplayName("Should throw EntityAlreadyExistsException when size already exists for color")
    void shouldThrowEntityAlreadyExistsExceptionWhenSizeExists() {
      Long productId = 1L;
      Long colorId = 1L;
      ProductVariantRequestDTO request = new ProductVariantRequestDTO(ProductSize.M, 10);

      when(productVariantRepository.existsByProductColorIdAndSize(colorId, request.size()))
          .thenReturn(true);

      assertThrows(
          EntityAlreadyExistsException.class,
          () -> productVariantService.addVariant(productId, colorId, request));
      verify(productVariantRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when color not found")
    void shouldThrowEntityNotFoundExceptionWhenColorNotFound() {
      Long productId = 1L;
      Long colorId = 1L;
      ProductVariantRequestDTO request = new ProductVariantRequestDTO(ProductSize.M, 10);

      when(productVariantRepository.existsByProductColorIdAndSize(colorId, request.size()))
          .thenReturn(false);
      when(productColorRepository.findByIdAndProductId(colorId, productId))
          .thenReturn(Optional.empty());

      assertThrows(
          EntityNotFoundException.class,
          () -> productVariantService.addVariant(productId, colorId, request));
      verify(productVariantRepository, never()).save(any());
    }
  }

  @Nested
  @DisplayName("Tests for updateStock")
  class UpdateStockTests {

    @Test
    @DisplayName("Should update stock successfully")
    void shouldUpdateStockSuccessfully() {
      Long productId = 1L;
      Long colorId = 1L;
      Long variantId = 1L;
      ProductVariantPatchDTO request = new ProductVariantPatchDTO(20);
      ProductVariant variant =
          ProductVariantTestFactory.aVariant().withId(variantId).withStock(10).build();
      ProductVariantResponseDTO response =
          new ProductVariantResponseDTO(variantId, ProductSize.M, 20);

      when(productVariantRepository.findByIdAndProductColorIdAndProductColorProductId(
              variantId, colorId, productId))
          .thenReturn(Optional.of(variant));
      when(productVariantRepository.save(variant)).thenReturn(variant);
      when(productMapper.toVariantResponse(variant)).thenReturn(response);

      ProductVariantResponseDTO result =
          productVariantService.updateStock(productId, colorId, variantId, request);

      assertEquals(response, result);
      assertEquals(20, variant.getStock());
      verify(productVariantRepository).save(variant);
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when variant not found")
    void shouldThrowEntityNotFoundExceptionWhenVariantNotFound() {
      Long productId = 1L;
      Long colorId = 1L;
      Long variantId = 1L;
      ProductVariantPatchDTO request = new ProductVariantPatchDTO(20);

      when(productVariantRepository.findByIdAndProductColorIdAndProductColorProductId(
              variantId, colorId, productId))
          .thenReturn(Optional.empty());

      assertThrows(
          EntityNotFoundException.class,
          () -> productVariantService.updateStock(productId, colorId, variantId, request));
      verify(productVariantRepository, never()).save(any());
    }
  }

  @Nested
  @DisplayName("Tests for delete")
  class DeleteTests {

    @Test
    @DisplayName("Should delete variant and keep product active if other variants exist")
    void shouldDeleteVariantAndKeepProductActive() {
      Long productId = 1L;
      Long colorId = 1L;
      Long variantId = 1L;

      Product product = ProductTestFactory.aProduct().withStatus(ProductStatus.ACTIVE).build();
      ProductColor color = ProductColorTestFactory.aColor().withProduct(product).build();
      ProductVariant variantToDelete =
          ProductVariantTestFactory.aVariant().withId(variantId).withProductColor(color).build();
      ProductVariant otherVariant =
          ProductVariantTestFactory.aVariant().withId(2L).withProductColor(color).build();

      color.setVariants(new java.util.HashSet<>(List.of(variantToDelete, otherVariant)));

      when(productVariantRepository.findByIdAndProductColorIdAndProductColorProductId(
              variantId, colorId, productId))
          .thenReturn(Optional.of(variantToDelete));

      productVariantService.delete(productId, colorId, variantId);

      verify(productVariantRepository).delete(variantToDelete);
      assertEquals(1, color.getVariants().size());
      assertEquals(ProductStatus.ACTIVE, product.getStatus());
    }

    @Test
    @DisplayName("Should delete variant and set product to draft if no variants left")
    void shouldDeleteVariantAndSetProductToDraft() {
      Long productId = 1L;
      Long colorId = 1L;
      Long variantId = 1L;

      Product product = ProductTestFactory.aProduct().withStatus(ProductStatus.ACTIVE).build();
      ProductColor color = ProductColorTestFactory.aColor().withProduct(product).build();
      ProductVariant variantToDelete =
          ProductVariantTestFactory.aVariant().withId(variantId).withProductColor(color).build();

      color.setVariants(new java.util.HashSet<>(List.of(variantToDelete)));

      when(productVariantRepository.findByIdAndProductColorIdAndProductColorProductId(
              variantId, colorId, productId))
          .thenReturn(Optional.of(variantToDelete));

      productVariantService.delete(productId, colorId, variantId);

      verify(productVariantRepository).delete(variantToDelete);
      assertEquals(0, color.getVariants().size());
      assertEquals(ProductStatus.DRAFT, product.getStatus());
    }

    @Test
    @DisplayName("Should not change status when ARCHIVED product loses last variant")
    void shouldKeepArchivedStatusWhenLastVariantDeleted() {
      Long productId = 1L;
      Long colorId = 1L;
      Long variantId = 1L;

      Product product = ProductTestFactory.aProduct().withStatus(ProductStatus.ARCHIVED).build();
      ProductColor color = ProductColorTestFactory.aColor().withProduct(product).build();
      ProductVariant variantToDelete =
          ProductVariantTestFactory.aVariant().withId(variantId).withProductColor(color).build();

      color.setVariants(new java.util.HashSet<>(List.of(variantToDelete)));

      when(productVariantRepository.findByIdAndProductColorIdAndProductColorProductId(
              variantId, colorId, productId))
          .thenReturn(Optional.of(variantToDelete));

      productVariantService.delete(productId, colorId, variantId);

      verify(productVariantRepository).delete(variantToDelete);
      assertEquals(ProductStatus.ARCHIVED, product.getStatus());
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when variant not found for deletion")
    void shouldThrowEntityNotFoundExceptionWhenVariantNotFoundForDeletion() {
      Long productId = 1L;
      Long colorId = 1L;
      Long variantId = 1L;

      when(productVariantRepository.findByIdAndProductColorIdAndProductColorProductId(
              variantId, colorId, productId))
          .thenReturn(Optional.empty());

      assertThrows(
          EntityNotFoundException.class,
          () -> productVariantService.delete(productId, colorId, variantId));
      verify(productVariantRepository, never()).delete(any());
    }
  }
}
