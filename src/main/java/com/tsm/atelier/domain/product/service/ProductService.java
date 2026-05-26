package com.tsm.atelier.domain.product.service;

import com.tsm.atelier.domain.collection.Collection;
import com.tsm.atelier.domain.collection.CollectionRepository;
import com.tsm.atelier.domain.product.Product;
import com.tsm.atelier.domain.product.ProductStatus;
import com.tsm.atelier.domain.product.ProductValidator;
import com.tsm.atelier.domain.product.dto.v1.internal.ProductIntegrityDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductCompositionRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductFilterDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductPatchDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductDetailsResponseDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductSummaryResponseDTO;
import com.tsm.atelier.domain.product.mapper.ProductMapper;
import com.tsm.atelier.domain.product.repository.ProductRepository;
import com.tsm.atelier.domain.product.repository.ProductSpecification;
import com.tsm.atelier.exception.BusinessException;
import com.tsm.atelier.exception.EntityAlreadyExistsException;
import com.tsm.atelier.exception.EntityNotFoundException;
import com.tsm.atelier.shared.util.SlugUtils;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final CollectionRepository collectionRepository;
  private final ProductValidator productValidator;

  @Transactional
  public ProductDetailsResponseDTO create(ProductRequestDTO request) {
    validateProductNameAvailability(request.name());
    productValidator.validateCompositions(request.compositions());
    productValidator.validateCategoryForAudience(request.category(), request.targetAudience());

    Product product = productMapper.toEntity(request);
    product.setSlug(generateUniqueSlug(request.name()));
    product.setCollection(resolveCollection(request.collectionId()));

    return productMapper.toDetailsResponse(productRepository.save(product));
  }

  @Transactional
  public ProductDetailsResponseDTO publish(Long id) {
    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produto", "id", id));

    if (product.getStatus() == ProductStatus.ACTIVE) {
      throw new BusinessException("Produto já está publicado");
    }

    if (product.getStatus() == ProductStatus.ARCHIVED) {
      throw new BusinessException(
          "Produto arquivado não pode ser publicado diretamente. Reative-o primeiro.");
    }

    ProductIntegrityDTO integrity =
        productRepository
            .findIntegrityById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produto", "id", id));
    productValidator.validateForPublication(integrity);

    product.setStatus(ProductStatus.ACTIVE);
    return productMapper.toDetailsResponse(productRepository.save(product));
  }

  @Transactional(readOnly = true)
  public Page<ProductSummaryResponseDTO> findAllFiltered(
      ProductFilterDTO filter, Pageable pageable) {
    Specification<Product> spec =
        Specification.where(ProductSpecification.withStatusActive())
            .and(ProductSpecification.hasNameContaining(filter.query()))
            .and(ProductSpecification.hasCategory(filter.category()))
            .and(ProductSpecification.hasCollection(filter.collectionId()))
            .and(ProductSpecification.hasPriceBetween(filter.minPrice(), filter.maxPrice()))
            .and(ProductSpecification.hasSizeAndStock(filter.productSize()));

    Page<Product> productPage = productRepository.findAll(spec, pageable);
    return productPage.map(productMapper::toSummaryResponse);
  }

  @Transactional(readOnly = true)
  public ProductDetailsResponseDTO findProductDetailsById(Long id) {
    Product product =
        productRepository
            .findProductDetailsById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produto", "id", id));

    return productMapper.toDetailsResponse(product);
  }

  @Transactional(readOnly = true)
  public ProductDetailsResponseDTO findProductDetailsBySlug(String slug) {
    Product product =
        productRepository
            .findBySlugAndStatus(slug, ProductStatus.ACTIVE)
            .orElseThrow(() -> new EntityNotFoundException("Produto", "slug", slug));

    return productMapper.toDetailsResponse(product);
  }

  @Transactional(readOnly = true)
  public Slice<ProductSummaryResponseDTO> findByCollectionId(Long collectionId, Pageable pageable) {
    if (!collectionRepository.existsById(collectionId)) {
      throw new EntityNotFoundException("Coleção", "id", collectionId);
    }

    Slice<Long> ids = productRepository.findIdsByCollectionId(collectionId, pageable);
    List<ProductSummaryResponseDTO> content =
        hydrateAndMap(ids.getContent(), productMapper::toSummaryResponse);
    return new SliceImpl<>(content, pageable, ids.hasNext());
  }

  private <T> List<T> hydrateAndMap(List<Long> ids, Function<Product, T> mapper) {
    if (ids.isEmpty()) return List.of();

    Map<Long, Product> byId =
        productRepository.findByIdIn(ids).stream()
            .collect(Collectors.toMap(Product::getId, Function.identity()));

    return ids.stream().map(byId::get).filter(Objects::nonNull).map(mapper).toList();
  }

  @Transactional
  public ProductDetailsResponseDTO partialUpdate(Long id, ProductPatchDTO request) {
    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produto", "id", id));

    request.name().ifPresent(newName -> updateName(product, newName));
    request.description().ifPresent(product::setDescription);
    request.price().ifPresent(product::setPrice);
    request.promotionalPrice().ifPresent(product::setPromotionalPrice);
    request.category().ifPresent(product::setCategory);
    request.targetAudience().ifPresent(product::setTargetAudience);
    request.material().ifPresent(newCompositions -> updateCompositions(product, newCompositions));
    request.careInstructions().ifPresent(product::setCareInstructions);

    productValidator.validateCategoryForAudience(
        product.getCategory(), product.getTargetAudience());

    return productMapper.toDetailsResponse(productRepository.save(product));
  }

  @Transactional
  public ProductDetailsResponseDTO archive(Long id) {
    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produto", "id", id));

    if (product.getStatus() == ProductStatus.ARCHIVED) {
      throw new BusinessException("Produto já está arquivado");
    }

    product.setStatus(ProductStatus.ARCHIVED);
    product.setDisabledAt(Instant.now());
    return productMapper.toDetailsResponse(productRepository.save(product));
  }

  private Collection resolveCollection(Long collectionId) {
    if (collectionId == null) return null;

    return collectionRepository
        .findById(collectionId)
        .orElseThrow(() -> new EntityNotFoundException("Coleção", "id", collectionId));
  }

  private void updateName(Product product, String newName) {
    if (product.getName().equals(newName)) return;

    validateNameAvailability(product.getId(), newName);

    product.setName(newName);
    product.setSlug(generateUniqueSlug(newName));
  }

  private void validateNameAvailability(Long productId, String name) {
    productRepository
        .findByName(name)
        .ifPresent(
            existing -> {
              if (!existing.getId().equals(productId)) {
                throw new EntityAlreadyExistsException("Produto", "nome", name);
              }
            });
  }

  private void validateProductNameAvailability(String name) {
    productRepository
        .findByName(name)
        .ifPresent(
            existing -> {
              String message =
                  switch (existing.getStatus()) {
                    case ACTIVE -> "Produto com este nome já está ativo";
                    case ARCHIVED ->
                        "Já existe um produto arquivado com este nome. Deseja reativá-lo?";
                    case DRAFT -> "Já existe um rascunho com este nome. Deseja editá-lo?";
                  };
              throw new EntityAlreadyExistsException(message);
            });
  }

  private void updateCompositions(
      Product product, List<ProductCompositionRequestDTO> compositions) {
    productValidator.validateCompositions(compositions);
    product.getCompositions().clear();
    compositions.forEach(compDTO -> product.addComposition(productMapper.toEntity(compDTO)));
  }

  private String generateUniqueSlug(String name) {
    String base = SlugUtils.generate(name);
    if (!productRepository.existsBySlug(base)) return base;

    int count = 1;
    String alias;
    do {
      alias = base + "-" + count++;
    } while (productRepository.existsBySlug(alias));
    return alias;
  }
}
