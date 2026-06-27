package com.tsm.atelier.domain.product.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tsm.atelier.domain.collection.Collection;
import com.tsm.atelier.domain.collection.CollectionRepository;
import com.tsm.atelier.domain.product.Product;
import com.tsm.atelier.domain.product.ProductStatus;
import com.tsm.atelier.domain.product.ProductValidator;
import com.tsm.atelier.domain.product.dto.v1.internal.ProductIntegrityDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductCompositionRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductPatchDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductDetailsResponseDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductSummaryResponseDTO;
import com.tsm.atelier.domain.product.mapper.ProductMapper;
import com.tsm.atelier.domain.product.repository.ProductRepository;
import com.tsm.atelier.exception.BusinessException;
import com.tsm.atelier.exception.EntityAlreadyExistsException;
import com.tsm.atelier.exception.EntityNotFoundException;
import com.tsm.atelier.factory.CollectionTestFactory;
import com.tsm.atelier.factory.ProductCompositionTestFactory;
import com.tsm.atelier.factory.ProductTestFactory;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProductService")
class ProductServiceTest {

  @Mock private ProductRepository productRepository;
  @Mock private ProductMapper productMapper;
  @Mock private CollectionRepository collectionRepository;
  @Mock private ProductValidator productValidator;

  @InjectMocks private ProductService productService;

  // ─── create ──────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Criar produto")
  class CreateProduct {

    @Test
    @DisplayName("Deve criar produto com sucesso quando nome não existe")
    void shouldCreateProductSuccessfullyWhenNameDoesNotExist() {
      // Arrange
      ProductRequestDTO request = ProductTestFactory.aProductRequest().build();
      Product product = ProductTestFactory.aProduct().withStatus(ProductStatus.DRAFT).build();
      Collection collection = CollectionTestFactory.aCollection().build();
      ProductDetailsResponseDTO expectedResponse = mock(ProductDetailsResponseDTO.class);

      when(productRepository.findByName(request.name())).thenReturn(Optional.empty());
      when(productRepository.existsBySlug(any(String.class))).thenReturn(false);
      when(productMapper.toEntity(request)).thenReturn(product);
      when(collectionRepository.findById(request.collectionId()))
          .thenReturn(Optional.of(collection));
      when(productRepository.save(any())).thenReturn(product);
      when(productMapper.toDetailsResponse(product)).thenReturn(expectedResponse);

      // Act
      ProductDetailsResponseDTO response = productService.create(request);

      // Assert
      assertThat(response).isEqualTo(expectedResponse);
      verify(productRepository).save(any(Product.class));
    }

    @Test
    @DisplayName("Deve gerar slug a partir do nome do produto")
    void shouldGenerateSlugFromProductName() {
      // Arrange
      ProductRequestDTO request =
          ProductTestFactory.aProductRequest().withName("Vestido Linho Premium").build();
      Product product = ProductTestFactory.aProduct().build();
      Collection collection = CollectionTestFactory.aCollection().build();

      when(productRepository.findByName(any())).thenReturn(Optional.empty());
      when(productRepository.existsBySlug("vestido-linho-premium")).thenReturn(false);
      when(productMapper.toEntity(request)).thenReturn(product);
      when(collectionRepository.findById(any())).thenReturn(Optional.of(collection));
      when(productRepository.save(any())).thenReturn(product);
      when(productMapper.toDetailsResponse(any()))
          .thenReturn(mock(ProductDetailsResponseDTO.class));

      // Act
      productService.create(request);

      // Assert
      assertThat(product.getSlug()).isEqualTo("vestido-linho-premium");
    }

    @Test
    @DisplayName("Deve criar produto sem coleção quando collectionId é nulo")
    void shouldCreateProductWithoutCollection() {
      // Arrange
      ProductRequestDTO request = ProductTestFactory.aProductRequest().withoutCollection().build();
      Product product = ProductTestFactory.aProduct().withoutCollection().build();

      when(productRepository.findByName(any())).thenReturn(Optional.empty());
      when(productRepository.existsBySlug(any(String.class))).thenReturn(false);
      when(productMapper.toEntity(request)).thenReturn(product);
      when(productRepository.save(any())).thenReturn(product);
      when(productMapper.toDetailsResponse(any()))
          .thenReturn(mock(ProductDetailsResponseDTO.class));

      // Act
      productService.create(request);

      // Assert
      verify(collectionRepository, never()).findById(any());
      assertThat(product.getCollection()).isNull();
    }

    @Test
    @DisplayName("Deve lançar exceção quando produto ACTIVE com mesmo nome existe")
    void shouldThrowWhenActiveProductWithSameNameExists() {
      // Arrange
      ProductRequestDTO request = ProductTestFactory.aProductRequest().build();
      Product existing = ProductTestFactory.aProduct().withStatus(ProductStatus.ACTIVE).build();

      when(productRepository.findByName(request.name())).thenReturn(Optional.of(existing));

      // Act & Assert
      assertThatThrownBy(() -> productService.create(request))
          .isInstanceOf(EntityAlreadyExistsException.class)
          .hasMessageContaining("ativo");

      verify(productRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando produto ARCHIVED com mesmo nome existe")
    void shouldThrowWhenArchivedProductWithSameNameExists() {
      // Arrange
      ProductRequestDTO request = ProductTestFactory.aProductRequest().build();
      Product existing = ProductTestFactory.aProduct().withStatus(ProductStatus.ARCHIVED).build();

      when(productRepository.findByName(request.name())).thenReturn(Optional.of(existing));

      // Act & Assert
      assertThatThrownBy(() -> productService.create(request))
          .isInstanceOf(EntityAlreadyExistsException.class)
          .hasMessageContaining("arquivado");

      verify(productRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando produto DRAFT com mesmo nome existe")
    void shouldThrowWhenDraftProductWithSameNameExists() {
      // Arrange
      ProductRequestDTO request = ProductTestFactory.aProductRequest().build();
      Product existing = ProductTestFactory.aProduct().withStatus(ProductStatus.DRAFT).build();

      when(productRepository.findByName(request.name())).thenReturn(Optional.of(existing));

      // Act & Assert
      assertThatThrownBy(() -> productService.create(request))
          .isInstanceOf(EntityAlreadyExistsException.class)
          .hasMessageContaining("rascunho");

      verify(productRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando coleção não é encontrada")
    void shouldThrowWhenCollectionNotFound() {
      // Arrange
      ProductRequestDTO request = ProductTestFactory.aProductRequest().build();
      Product product = ProductTestFactory.aProduct().build();

      when(productRepository.findByName(any())).thenReturn(Optional.empty());
      when(productRepository.existsBySlug(any(String.class))).thenReturn(false);
      when(productMapper.toEntity(request)).thenReturn(product);
      when(collectionRepository.findById(any())).thenReturn(Optional.empty());

      // Act & Assert
      assertThatThrownBy(() -> productService.create(request))
          .isInstanceOf(EntityNotFoundException.class)
          .hasMessageContaining("Coleção");

      verify(productRepository, never()).save(any());
    }
  }

  // ─── publish ─────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Publicar produto")
  class PublishProduct {

    @Test
    @DisplayName("Deve publicar produto DRAFT com sucesso")
    void shouldPublishDraftProductSuccessfully() {
      // Arrange
      Product product = ProductTestFactory.aProduct().withStatus(ProductStatus.DRAFT).build();
      ProductIntegrityDTO integrity = mock(ProductIntegrityDTO.class);
      ProductDetailsResponseDTO expectedResponse = mock(ProductDetailsResponseDTO.class);

      when(productRepository.findById(1L)).thenReturn(Optional.of(product));
      when(productRepository.findIntegrityById(1L)).thenReturn(Optional.of(integrity));
      when(productRepository.save(any())).thenReturn(product);
      when(productMapper.toDetailsResponse(product)).thenReturn(expectedResponse);

      // Act
      ProductDetailsResponseDTO result = productService.publish(1L);

      // Assert
      assertThat(product.getStatus()).isEqualTo(ProductStatus.ACTIVE);
      assertThat(result).isSameAs(expectedResponse);
      verify(productRepository).save(product);
      verify(productValidator).validateForPublication(integrity);
    }

    @Test
    @DisplayName("Deve lançar exceção ao publicar produto já ACTIVE")
    void shouldThrowExceptionWhenPublishingAlreadyActiveProduct() {
      // Arrange
      Product product = ProductTestFactory.aProduct().withStatus(ProductStatus.ACTIVE).build();

      when(productRepository.findById(1L)).thenReturn(Optional.of(product));

      // Act & Assert
      assertThatThrownBy(() -> productService.publish(1L))
          .isInstanceOf(BusinessException.class)
          .hasMessageContaining("já está publicado");

      verify(productRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção ao publicar produto ARCHIVED")
    void shouldThrowExceptionWhenPublishingArchivedProduct() {
      // Arrange
      Product product = ProductTestFactory.aProduct().withStatus(ProductStatus.ARCHIVED).build();

      when(productRepository.findById(1L)).thenReturn(Optional.of(product));

      // Act & Assert
      assertThatThrownBy(() -> productService.publish(1L))
          .isInstanceOf(BusinessException.class)
          .hasMessageContaining("arquivado");

      verify(productRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção ao publicar produto inexistente")
    void shouldThrowExceptionWhenPublishingNonExistentProduct() {
      // Arrange
      when(productRepository.findById(99L)).thenReturn(Optional.empty());

      // Act & Assert
      assertThatThrownBy(() -> productService.publish(99L))
          .isInstanceOf(EntityNotFoundException.class)
          .hasMessageContaining("Produto");
    }

    @Test
    @DisplayName("Deve chamar validator antes de publicar")
    void shouldCallValidatorBeforePublishing() {
      // Arrange
      Product product = ProductTestFactory.aProduct().withStatus(ProductStatus.DRAFT).build();
      ProductIntegrityDTO integrity = mock(ProductIntegrityDTO.class);

      when(productRepository.findById(1L)).thenReturn(Optional.of(product));
      when(productRepository.findIntegrityById(1L)).thenReturn(Optional.of(integrity));
      when(productRepository.save(any())).thenReturn(product);
      when(productMapper.toDetailsResponse(any()))
          .thenReturn(mock(ProductDetailsResponseDTO.class));

      // Act
      productService.publish(1L);

      // Assert
      verify(productValidator).validateForPublication(integrity);
    }

    @Test
    @DisplayName("Deve lançar EntityNotFoundException quando integrity não retorna nada")
    void shouldThrowWhenIntegrityIsEmpty() {
      // Arrange
      Product product = ProductTestFactory.aProduct().withStatus(ProductStatus.DRAFT).build();

      when(productRepository.findById(1L)).thenReturn(Optional.of(product));
      when(productRepository.findIntegrityById(1L)).thenReturn(Optional.empty());

      // Act & Assert
      assertThatThrownBy(() -> productService.publish(1L))
          .isInstanceOf(EntityNotFoundException.class);

      verify(productValidator, never()).validateForPublication(any());
      verify(productRepository, never()).save(any());
      assertThat(product.getStatus()).isEqualTo(ProductStatus.DRAFT);
    }

    @Test
    @DisplayName("Deve propagar BusinessException do validator e não publicar o produto")
    void shouldPropagateValidatorFailure() {
      // Arrange
      Product product = ProductTestFactory.aProduct().withStatus(ProductStatus.DRAFT).build();
      ProductIntegrityDTO integrity = mock(ProductIntegrityDTO.class);

      when(productRepository.findById(1L)).thenReturn(Optional.of(product));
      when(productRepository.findIntegrityById(1L)).thenReturn(Optional.of(integrity));
      doThrow(new BusinessException("Produto incompleto"))
          .when(productValidator)
          .validateForPublication(integrity);

      // Act & Assert
      assertThatThrownBy(() -> productService.publish(1L))
          .isInstanceOf(BusinessException.class)
          .hasMessageContaining("incompleto");

      assertThat(product.getStatus()).isEqualTo(ProductStatus.DRAFT);
      verify(productRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve retornar o DTO mapeado em caso de sucesso")
    void shouldReturnMappedResponseOnSuccess() {
      // Arrange
      Product product = ProductTestFactory.aProduct().withStatus(ProductStatus.DRAFT).build();
      ProductIntegrityDTO integrity = mock(ProductIntegrityDTO.class);
      ProductDetailsResponseDTO expected = mock(ProductDetailsResponseDTO.class);

      when(productRepository.findById(1L)).thenReturn(Optional.of(product));
      when(productRepository.findIntegrityById(1L)).thenReturn(Optional.of(integrity));
      when(productRepository.save(product)).thenReturn(product);
      when(productMapper.toDetailsResponse(product)).thenReturn(expected);

      // Act
      ProductDetailsResponseDTO result = productService.publish(1L);

      // Assert
      assertThat(result).isSameAs(expected);
    }
  }

  // ─── archive ─────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Arquivar produto")
  class ArchiveProduct {

    @Test
    @DisplayName("Deve arquivar produto ACTIVE com sucesso")
    void shouldArchiveActiveProductSuccessfully() {
      // Arrange
      Product product = ProductTestFactory.aProduct().withStatus(ProductStatus.ACTIVE).build();
      ProductDetailsResponseDTO expectedResponse = mock(ProductDetailsResponseDTO.class);

      when(productRepository.findById(1L)).thenReturn(Optional.of(product));
      when(productRepository.save(any())).thenReturn(product);
      when(productMapper.toDetailsResponse(product)).thenReturn(expectedResponse);

      // Act
      ProductDetailsResponseDTO result = productService.archive(1L);

      // Assert
      assertThat(product.getStatus()).isEqualTo(ProductStatus.ARCHIVED);
      assertThat(product.getDisabledAt()).isNotNull();
      assertThat(result).isSameAs(expectedResponse);
      verify(productRepository).save(product);
    }

    @Test
    @DisplayName("Deve lançar exceção ao arquivar produto já ARCHIVED")
    void shouldThrowExceptionWhenArchivingAlreadyArchivedProduct() {
      // Arrange
      Product product = ProductTestFactory.aProduct().withStatus(ProductStatus.ARCHIVED).build();

      when(productRepository.findById(1L)).thenReturn(Optional.of(product));

      // Act & Assert
      assertThatThrownBy(() -> productService.archive(1L))
          .isInstanceOf(BusinessException.class)
          .hasMessageContaining("já está arquivado");

      verify(productRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção ao arquivar produto inexistente")
    void shouldThrowExceptionWhenArchivingNonExistentProduct() {
      // Arrange
      when(productRepository.findById(99L)).thenReturn(Optional.empty());

      // Act & Assert
      assertThatThrownBy(() -> productService.archive(99L))
          .isInstanceOf(EntityNotFoundException.class)
          .hasMessageContaining("Produto");
    }
  }

  // ─── partialUpdate ────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Atualizar produto parcialmente")
  class PartialUpdateProduct {

    @Test
    @DisplayName("Deve atualizar nome e slug com sucesso")
    void shouldUpdateNameAndSlugSuccessfully() {
      // Arrange
      Product product =
          ProductTestFactory.aProduct().withName("Nome Antigo").withSlug("nome-antigo").build();
      ProductPatchDTO request =
          new ProductPatchDTO(
              Optional.of("Nome Novo"),
              Optional.empty(),
              Optional.empty(),
              Optional.empty(),
              Optional.empty(),
              Optional.empty(),
              Optional.empty(),
              Optional.empty());

      when(productRepository.findById(1L)).thenReturn(Optional.of(product));
      when(productRepository.findByName("Nome Novo")).thenReturn(Optional.empty());
      when(productRepository.existsBySlug("nome-novo")).thenReturn(false);
      when(productRepository.save(any())).thenReturn(product);
      when(productMapper.toDetailsResponse(any()))
          .thenReturn(mock(ProductDetailsResponseDTO.class));

      // Act
      productService.partialUpdate(1L, request);

      // Assert
      assertThat(product.getName()).isEqualTo("Nome Novo");
      assertThat(product.getSlug()).isEqualTo("nome-novo");
    }

    @Test
    @DisplayName("Não deve atualizar slug quando nome não muda")
    void shouldNotUpdateSlugWhenNameDoesNotChange() {
      // Arrange
      Product product =
          ProductTestFactory.aProduct().withName("Mesmo Nome").withSlug("mesmo-nome").build();
      ProductPatchDTO request =
          new ProductPatchDTO(
              Optional.of("Mesmo Nome"),
              Optional.empty(),
              Optional.empty(),
              Optional.empty(),
              Optional.empty(),
              Optional.empty(),
              Optional.empty(),
              Optional.empty());

      when(productRepository.findById(1L)).thenReturn(Optional.of(product));
      when(productRepository.save(any())).thenReturn(product);
      when(productMapper.toDetailsResponse(any()))
          .thenReturn(mock(ProductDetailsResponseDTO.class));

      // Act
      productService.partialUpdate(1L, request);

      // Assert
      assertThat(product.getSlug()).isEqualTo("mesmo-nome");
      verify(productRepository, never()).findByName(any());
    }

    @Test
    @DisplayName("Deve atualizar apenas o preço sem alterar outros campos")
    void shouldUpdateOnlyPrice() {
      // Arrange
      Product product = ProductTestFactory.aProduct().build();
      String originalName = product.getName();
      String originalSlug = product.getSlug();
      ProductPatchDTO request =
          new ProductPatchDTO(
              Optional.empty(),
              Optional.empty(),
              Optional.of(BigDecimal.valueOf(499.90)),
              Optional.empty(),
              Optional.empty(),
              Optional.empty(),
              Optional.empty(),
              Optional.empty());

      when(productRepository.findById(1L)).thenReturn(Optional.of(product));
      when(productRepository.save(any())).thenReturn(product);
      when(productMapper.toDetailsResponse(any()))
          .thenReturn(mock(ProductDetailsResponseDTO.class));

      // Act
      productService.partialUpdate(1L, request);

      // Assert
      assertThat(product.getPrice()).isEqualTo(BigDecimal.valueOf(499.90));
      assertThat(product.getName()).isEqualTo(originalName);
      assertThat(product.getSlug()).isEqualTo(originalSlug);
    }

    @Test
    @DisplayName("Deve substituir composições completamente")
    void shouldReplaceCompositionsCompletely() {
      // Arrange
      Product product =
          ProductTestFactory.aProduct()
              .withCompositions(
                  new java.util.ArrayList<>(
                      List.of(ProductCompositionTestFactory.aProductComposition().build())))
              .build();
      List<ProductCompositionRequestDTO> newCompositions =
          List.of(ProductCompositionTestFactory.aProductCompositionRequest());
      ProductPatchDTO request =
          new ProductPatchDTO(
              Optional.empty(),
              Optional.empty(),
              Optional.empty(),
              Optional.empty(),
              Optional.of(newCompositions),
              Optional.empty(),
              Optional.empty(),
              Optional.empty());

      when(productRepository.findById(1L)).thenReturn(Optional.of(product));
      when(productMapper.toEntity(any(ProductCompositionRequestDTO.class)))
          .thenReturn(ProductCompositionTestFactory.aProductComposition().build());
      when(productRepository.save(any())).thenReturn(product);
      when(productMapper.toDetailsResponse(any()))
          .thenReturn(mock(ProductDetailsResponseDTO.class));

      // Act
      productService.partialUpdate(1L, request);

      // Assert
      assertThat(product.getCompositions()).hasSize(1);
    }

    @Test
    @DisplayName("Deve lançar exceção quando novo nome já pertence a outro produto")
    void shouldThrowWhenNewNameBelongsToAnotherProduct() {
      // Arrange
      Product product =
          ProductTestFactory.aProduct().withId(1L).withName("Nome Antigo").build();
      Product anotherProduct =
          ProductTestFactory.aProduct().withId(2L).withName("Nome Existente").build();

      ProductPatchDTO request =
          new ProductPatchDTO(
              Optional.of("Nome Existente"),
              Optional.empty(),
              Optional.empty(),
              Optional.empty(),
              Optional.empty(),
              Optional.empty(),
              Optional.empty(),
              Optional.empty());

      when(productRepository.findById(1L)).thenReturn(Optional.of(product));
      when(productRepository.findByName("Nome Existente"))
          .thenReturn(Optional.of(anotherProduct));

      // Act & Assert
      assertThatThrownBy(() -> productService.partialUpdate(1L, request))
          .isInstanceOf(EntityAlreadyExistsException.class)
          .hasMessageContaining("Produto");

      verify(productRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve permitir atualizar para o mesmo nome do próprio produto")
    void shouldAllowUpdatingToSameProductName() {
      // Arrange
      Product product =
          ProductTestFactory.aProduct().withId(1L).withName("Nome Original").build();
      ProductPatchDTO request =
          new ProductPatchDTO(
              Optional.of("Nome Atualizado"),
              Optional.empty(),
              Optional.empty(),
              Optional.empty(),
              Optional.empty(),
              Optional.empty(),
              Optional.empty(),
              Optional.empty());

      when(productRepository.findById(1L)).thenReturn(Optional.of(product));
      when(productRepository.findByName("Nome Atualizado"))
          .thenReturn(Optional.of(product)); // mesmo produto — deve ser permitido
      when(productRepository.existsBySlug(any(String.class))).thenReturn(false);
      when(productRepository.save(any())).thenReturn(product);
      when(productMapper.toDetailsResponse(any()))
          .thenReturn(mock(ProductDetailsResponseDTO.class));

      // Act & Assert — não deve lançar exceção
      assertThatNoException().isThrownBy(() -> productService.partialUpdate(1L, request));
    }

    @Test
    @DisplayName("Deve lançar exceção quando produto não existe")
    void shouldThrowWhenProductNotFound() {
      // Arrange
      ProductPatchDTO request =
          new ProductPatchDTO(
              Optional.of("Novo Nome"),
              Optional.empty(),
              Optional.empty(),
              Optional.empty(),
              Optional.empty(),
              Optional.empty(),
              Optional.empty(),
              Optional.empty());

      when(productRepository.findById(99L)).thenReturn(Optional.empty());

      // Act & Assert
      assertThatThrownBy(() -> productService.partialUpdate(99L, request))
          .isInstanceOf(EntityNotFoundException.class)
          .hasMessageContaining("Produto");
    }
  }

  // ─── findByCollectionId ───────────────────────────────────────────────────

  @Nested
  @DisplayName("Buscar produtos por coleção")
  class FindByCollection {

    @Test
    @DisplayName("Deve retornar produtos da coleção com sucesso")
    void shouldReturnProductsByCollectionSuccessfully() {
      // Arrange
      Pageable pageable = PageRequest.of(0, 10);
      Product product = ProductTestFactory.aProduct().withId(1L).build();
      Slice<Long> idSlice = new SliceImpl<>(List.of(1L), pageable, false);

      when(collectionRepository.existsById(1L)).thenReturn(true);
      when(productRepository.findIdsByCollectionId(1L, pageable)).thenReturn(idSlice);
      when(productRepository.findByIdIn(List.of(1L))).thenReturn(List.of(product));
      when(productMapper.toSummaryResponse(any()))
          .thenReturn(mock(ProductSummaryResponseDTO.class));

      // Act
      Slice<ProductSummaryResponseDTO> result = productService.findByCollectionId(1L, pageable);

      // Assert
      assertThat(result).isNotNull();
      assertThat(result.getContent()).hasSize(1);
    }

    @Test
    @DisplayName("Deve lançar exceção quando coleção não existe")
    void shouldThrowExceptionWhenCollectionNotFound() {
      // Arrange
      when(collectionRepository.existsById(99L)).thenReturn(false);

      // Act & Assert
      assertThatThrownBy(() -> productService.findByCollectionId(99L, PageRequest.of(0, 10)))
          .isInstanceOf(EntityNotFoundException.class)
          .hasMessageContaining("Coleção");
    }
  }
}
