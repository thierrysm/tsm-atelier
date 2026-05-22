package com.tsm.atelier.domain.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tsm.atelier.domain.product.Product;
import com.tsm.atelier.domain.product.ProductColor;
import com.tsm.atelier.domain.product.ProductImage;
import com.tsm.atelier.domain.product.ProductStatus;
import com.tsm.atelier.domain.product.dto.v1.request.ProductColorPatchDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductColorRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductColorResponseDTO;
import com.tsm.atelier.domain.product.mapper.ProductMapper;
import com.tsm.atelier.domain.product.repository.ProductColorRepository;
import com.tsm.atelier.domain.product.repository.ProductRepository;
import com.tsm.atelier.exception.EntityAlreadyExistsException;
import com.tsm.atelier.exception.EntityNotFoundException;
import com.tsm.atelier.factory.ProductColorTestFactory;
import com.tsm.atelier.factory.ProductTestFactory;
import com.tsm.atelier.shared.image.ImageService;
import java.util.ArrayList;
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
class ProductColorServiceTest {

  @Mock private ProductColorRepository productColorRepository;

  @Mock private ProductRepository productRepository;

  @Mock private ProductMapper productMapper;

  @Mock private ImageService imageService;

  @InjectMocks private ProductColorService productColorService;

  @Nested
  @DisplayName("Tests for addColorToProduct")
  class AddColorToProductTests {

    @Test
    @DisplayName("Should add color to product successfully")
    void shouldAddColorToProductSuccessfully() {
      Long productId = 1L;
      Product product = ProductTestFactory.aProduct().withId(productId).build();
      ProductColorRequestDTO request = ProductColorTestFactory.aColorRequest();
      ProductColor color = ProductColorTestFactory.aColor().withProduct(product).build();
      ProductColorResponseDTO response =
          new ProductColorResponseDTO(1L, request.name(), request.hexCode());

      when(productRepository.findById(productId)).thenReturn(Optional.of(product));
      when(productColorRepository.existsByProductIdAndNameIgnoreCase(productId, request.name()))
          .thenReturn(false);
      when(productColorRepository.existsByProductIdAndHexCodeIgnoreCase(
              productId, request.hexCode()))
          .thenReturn(false);
      when(productMapper.toEntity(request)).thenReturn(color);
      when(productColorRepository.save(color)).thenReturn(color);
      when(productMapper.toColorResponse(color)).thenReturn(response);

      ProductColorResponseDTO result = productColorService.addColorToProduct(productId, request);

      assertEquals(response, result);
      verify(productColorRepository).save(color);
      assertTrue(product.getColors().contains(color));
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when product does not exist")
    void shouldThrowExceptionWhenProductDoesNotExist() {
      Long productId = 1L;
      ProductColorRequestDTO request = ProductColorTestFactory.aColorRequest();
      when(productRepository.findById(productId)).thenReturn(Optional.empty());

      assertThrows(
          EntityNotFoundException.class,
          () -> productColorService.addColorToProduct(productId, request));
      verify(productColorRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should throw EntityAlreadyExistsException when color name exists for product")
    void shouldThrowExceptionWhenColorNameExists() {
      Long productId = 1L;
      ProductColorRequestDTO request = new ProductColorRequestDTO("Azul", "#0000FF");
      Product product = ProductTestFactory.aProduct().withId(productId).build();

      when(productRepository.findById(productId)).thenReturn(Optional.of(product));
      when(productColorRepository.existsByProductIdAndNameIgnoreCase(productId, "Azul"))
          .thenReturn(true);

      assertThrows(
          EntityAlreadyExistsException.class,
          () -> productColorService.addColorToProduct(productId, request));
      verify(productColorRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should throw EntityAlreadyExistsException when hex code exists for product")
    void shouldThrowExceptionWhenHexCodeExists() {
      Long productId = 1L;
      ProductColorRequestDTO request = new ProductColorRequestDTO("Marinho", "#0000FF");
      Product product = ProductTestFactory.aProduct().withId(productId).build();

      when(productRepository.findById(productId)).thenReturn(Optional.of(product));
      when(productColorRepository.existsByProductIdAndNameIgnoreCase(productId, "Marinho"))
          .thenReturn(false);
      when(productColorRepository.existsByProductIdAndHexCodeIgnoreCase(productId, "#0000FF"))
          .thenReturn(true);

      assertThrows(
          EntityAlreadyExistsException.class,
          () -> productColorService.addColorToProduct(productId, request));
    }
  }

  @Nested
  @DisplayName("Tests for updateColor")
  class UpdateColorTests {

    @Test
    @DisplayName("Should update color successfully")
    void shouldUpdateColorSuccessfully() {
      Long productId = 1L;
      Long colorId = 10L;
      Product product = ProductTestFactory.aProduct().withId(productId).build();
      ProductColor color =
          ProductColorTestFactory.aColor().withId(colorId).withProduct(product).build();

      ProductColorPatchDTO request =
          new ProductColorPatchDTO(Optional.of("Novo Nome"), Optional.of("#FFFFFF"));
      ProductColorResponseDTO response =
          new ProductColorResponseDTO(colorId, "Novo Nome", "#FFFFFF");

      when(productColorRepository.findByIdAndProductId(colorId, productId))
          .thenReturn(Optional.of(color));
      when(productColorRepository.existsByProductIdAndNameIgnoreCaseAndIdNot(
              anyLong(), anyString(), anyLong()))
          .thenReturn(false);
      when(productColorRepository.existsByProductIdAndHexCodeIgnoreCaseAndIdNot(
              anyLong(), anyString(), anyLong()))
          .thenReturn(false);
      when(productMapper.toColorResponse(color)).thenReturn(response);

      ProductColorResponseDTO result = productColorService.updateColor(colorId, productId, request);

      assertEquals(response, result);
      assertEquals("Novo Nome", color.getName());
      assertEquals("#FFFFFF", color.getHexCode());
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when color does not belong to product")
    void shouldThrowExceptionWhenColorDoesNotBelongToProduct() {
      Long productId = 1L;
      Long colorId = 10L;

      when(productColorRepository.findByIdAndProductId(colorId, productId))
          .thenReturn(Optional.empty());

      assertThrows(
          EntityNotFoundException.class,
          () ->
              productColorService.updateColor(
                  colorId,
                  productId,
                  new ProductColorPatchDTO(Optional.empty(), Optional.empty())));
    }

    @Test
    @DisplayName(
        "Should throw EntityAlreadyExistsException when updated name duplicates another color")
    void shouldThrowExceptionWhenUpdateDuplicatesAnotherColor() {
      Long productId = 1L;
      Long colorId = 10L;
      Product product = ProductTestFactory.aProduct().withId(productId).build();
      ProductColor color =
          ProductColorTestFactory.aColor()
              .withId(colorId)
              .withName("Azul")
              .withProduct(product)
              .build();

      ProductColorPatchDTO request =
          new ProductColorPatchDTO(Optional.of("Verde"), Optional.empty());

      when(productColorRepository.findByIdAndProductId(colorId, productId))
          .thenReturn(Optional.of(color));
      when(productColorRepository.existsByProductIdAndNameIgnoreCaseAndIdNot(
              productId, "Verde", colorId))
          .thenReturn(true);

      assertThrows(
          EntityAlreadyExistsException.class,
          () -> productColorService.updateColor(colorId, productId, request));
    }
  }

  @Nested
  @DisplayName("Tests for delete")
  class DeleteTests {

    @Test
    @DisplayName("Should delete color successfully")
    void shouldDeleteColorSuccessfully() {
      Long productId = 1L;
      Long colorId = 10L;
      Product product = ProductTestFactory.aProduct().withId(productId).build();
      ProductColor color =
          ProductColorTestFactory.aColor().withId(colorId).withProduct(product).build();

      ProductImage image = new ProductImage();
      image.setUrl("http://s3/test.jpg");
      color.getImages().add(image);

      ProductColor otherColor =
          ProductColorTestFactory.aColor().withId(11L).withProduct(product).build();
      product.setColors(new ArrayList<>(List.of(color, otherColor)));

      when(productColorRepository.findByIdAndProductId(colorId, productId))
          .thenReturn(Optional.of(color));

      productColorService.delete(colorId, productId);

      verify(imageService).deleteAfterCommit("http://s3/test.jpg");
      verify(productColorRepository).delete(color);
      assertEquals(1, product.getColors().size());
      assertEquals(ProductStatus.ACTIVE, product.getStatus());
    }

    @Test
    @DisplayName("Should change product status to DRAFT when last color is deleted")
    void shouldChangeStatusToDraftWhenLastColorDeleted() {
      Long productId = 1L;
      Long colorId = 10L;
      Product product =
          ProductTestFactory.aProduct().withId(productId).withStatus(ProductStatus.ACTIVE).build();
      ProductColor color =
          ProductColorTestFactory.aColor().withId(colorId).withProduct(product).build();
      product.setColors(new ArrayList<>(List.of(color)));

      when(productColorRepository.findByIdAndProductId(colorId, productId))
          .thenReturn(Optional.of(color));

      productColorService.delete(colorId, productId);

      verify(imageService, never()).deleteAfterCommit(anyString());
      verify(productColorRepository).delete(color);
      assertEquals(0, product.getColors().size());
      assertEquals(ProductStatus.DRAFT, product.getStatus());
    }

    @Test
    @DisplayName("Should not change status when ARCHIVED product loses last color")
    void shouldKeepArchivedStatusWhenLastColorDeleted() {
      Long productId = 1L;
      Long colorId = 10L;
      Product product =
          ProductTestFactory.aProduct()
              .withId(productId)
              .withStatus(ProductStatus.ARCHIVED)
              .build();
      ProductColor color =
          ProductColorTestFactory.aColor().withId(colorId).withProduct(product).build();
      product.setColors(new ArrayList<>(List.of(color)));

      when(productColorRepository.findByIdAndProductId(colorId, productId))
          .thenReturn(Optional.of(color));

      productColorService.delete(colorId, productId);

      verify(productColorRepository).delete(color);
      assertEquals(0, product.getColors().size());
      assertEquals(ProductStatus.ARCHIVED, product.getStatus());
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when deleting color from wrong product")
    void shouldThrowExceptionWhenDeletingFromWrongProduct() {
      Long productId = 1L;
      Long colorId = 10L;

      when(productColorRepository.findByIdAndProductId(colorId, productId))
          .thenReturn(Optional.empty());

      assertThrows(
          EntityNotFoundException.class, () -> productColorService.delete(colorId, productId));
      verify(productColorRepository, never()).delete(any());
      verify(imageService, never()).deleteAfterCommit(anyString());
    }
  }
}
