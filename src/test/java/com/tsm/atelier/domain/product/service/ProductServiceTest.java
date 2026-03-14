package com.tsm.atelier.domain.product.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tsm.atelier.domain.collection.Collection;
import com.tsm.atelier.domain.collection.CollectionRepository;
import com.tsm.atelier.domain.product.Product;
import com.tsm.atelier.domain.product.ProductStatus;
import com.tsm.atelier.domain.product.dto.v1.request.ProductRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductDetailsResponseDTO;
import com.tsm.atelier.domain.product.mapper.ProductMapper;
import com.tsm.atelier.domain.product.repository.ProductColorRepository;
import com.tsm.atelier.domain.product.repository.ProductRepository;
import com.tsm.atelier.exception.EntityAlreadyExistsException;
import com.tsm.atelier.factory.CollectionTestFactory;
import com.tsm.atelier.factory.ProductTestFactory;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProductService")
class ProductServiceTest {

  @Mock private ProductRepository productRepository;
  @Mock private ProductColorRepository productColorRepository;
  @Mock private CollectionRepository collectionRepository;
  @Mock private ProductMapper productMapper;

  @InjectMocks private ProductService productService;

  @Nested
  @DisplayName("Criar produto")
  class CreateProduct {

    @Test
    @DisplayName("Deve criar produto com sucesso com status DRAFT")
    void shouldCreateProductSuccessfullyWithStatusDraft() {
      // Arrange
      ProductRequestDTO request = ProductTestFactory.aProductRequest().build();
      Product product = ProductTestFactory.aProduct().withStatus(ProductStatus.DRAFT).build();
      Collection collection = CollectionTestFactory.aCollection().build();
      ProductDetailsResponseDTO expectedResponse = mock(ProductDetailsResponseDTO.class);

      when(productRepository.existsByNameAndStatus(request.name(), ProductStatus.ACTIVE))
          .thenReturn(false);
      when(productMapper.toEntity(request)).thenReturn(product);
      when(collectionRepository.findById(request.collectionId()))
          .thenReturn(Optional.of(collection));
      when(productRepository.save(any(Product.class))).thenReturn(product);
      when(productMapper.toDetailsResponse(any())).thenReturn(expectedResponse);

      // Act
      ProductDetailsResponseDTO response = productService.create(request);

      // Assert
      assertThat(response).isNotNull();
      assertThat(product.getStatus()).isEqualTo(ProductStatus.DRAFT);
      assertThat(product.getSlug()).isNotNull();
      verify(productRepository).save(any(Product.class));
    }

    @Test
    @DisplayName("Deve criar produto sem coleção quando collectionId é nulo")
    void shouldCreateProductWithoutCollection() {
      // Arrange
      ProductRequestDTO request = ProductTestFactory.aProductRequest().withoutCollection().build();
      Product product =
          ProductTestFactory.aProduct().withStatus(ProductStatus.DRAFT).withoutCollection().build();

      ProductDetailsResponseDTO expectedResponse = mock(ProductDetailsResponseDTO.class);

      when(productRepository.existsByNameAndStatus(request.name(), ProductStatus.ACTIVE))
          .thenReturn(false);
      when(productMapper.toEntity(request)).thenReturn(product);
      when(productRepository.save(any(Product.class))).thenReturn(product);
      when(productMapper.toDetailsResponse(any())).thenReturn(expectedResponse);

      // Act
      ProductDetailsResponseDTO response = productService.create(request);

      // Assert
      assertThat(response).isNotNull();
      assertThat(product.getStatus()).isEqualTo(ProductStatus.DRAFT);
      assertThat(product.getSlug()).isNotNull();
      assertThat(product.getCollection()).isNull();
      verify(productRepository).save(any(Product.class));
    }

    @Test
    @DisplayName("Deve lançar exceção quando produto com mesmo nome e status 'ACTIVE' já existe")
    void shouldThrowExceptionWhenProductNameAlreadyExists() {
      // Arrange
      ProductRequestDTO request = ProductTestFactory.aProductRequest().build();

      when(productRepository.existsByNameAndStatus(request.name(), ProductStatus.ACTIVE))
          .thenReturn(true);

      // Assert
      assertThatThrownBy(() -> productService.create(request))
          .isInstanceOf(EntityAlreadyExistsException.class)
          .hasMessageContaining("Produto");

      verify(productColorRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando produto com mesmo nome e status 'ARCHIVED' já existe")
    void shouldThrowExceptionWhenProductNameAlreadyExistsWithStatusArchived() {
      // Arrange
      ProductRequestDTO request = ProductTestFactory.aProductRequest().build();

      when(productRepository.existsByNameAndStatus(request.name(), ProductStatus.ACTIVE))
          .thenReturn(false);
      when(productRepository.existsByNameAndStatus(request.name(), ProductStatus.ARCHIVED))
          .thenReturn(true);

      // Assert
      assertThatThrownBy(() -> productService.create(request))
          .isInstanceOf(EntityAlreadyExistsException.class)
          .hasMessageContaining("Já existe um produto com este nome, deseja reativá-lo?");

      verify(productColorRepository, never()).save(any());
    }
  }
}
