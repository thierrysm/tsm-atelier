package com.tsm.atelier.domain.product.service;

import com.tsm.atelier.domain.collection.Collection;
import com.tsm.atelier.domain.collection.CollectionRepository;
import com.tsm.atelier.domain.product.Product;
import com.tsm.atelier.domain.product.ProductCare;
import com.tsm.atelier.domain.product.ProductColor;
import com.tsm.atelier.domain.product.ProductComposition;
import com.tsm.atelier.domain.product.ProductStatus;
import com.tsm.atelier.domain.product.dto.v1.request.ProductCompositionRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductPatchDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductDetailsResponseDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductSummaryResponseDTO;
import com.tsm.atelier.domain.product.mapper.ProductMapper;
import com.tsm.atelier.domain.product.repository.ProductCareRepository;
import com.tsm.atelier.domain.product.repository.ProductColorRepository;
import com.tsm.atelier.domain.product.repository.ProductCompositionRepository;
import com.tsm.atelier.domain.product.repository.ProductRepository;
import com.tsm.atelier.exception.EntityAlreadyExistsException;
import com.tsm.atelier.exception.EntityNotFoundException;
import com.tsm.atelier.shared.util.SlugUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final CollectionRepository collectionRepository;
  private final ProductCompositionRepository productCompositionRepository;
  private final ProductCareRepository productCareRepository;
  private final ProductColorRepository productColorRepository;
  private final ProductValidator productValidator;

  @Transactional
  public void publish(Long id) {
    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produto", "id", id));

    if (product.getStatus() == ProductStatus.ACTIVE) {
      return;
    }

    productRepository.findIntegrityById(id).ifPresent(productValidator::validateForPublication);

    product.setStatus(ProductStatus.ACTIVE);
    productRepository.save(product);
  }

  @Transactional(readOnly = true)
  public Slice<ProductSummaryResponseDTO> findAllSummary(Pageable pageable) {
    return productRepository
        .findAllByStatus(ProductStatus.ACTIVE, pageable)
        .map(productMapper::toSummaryResponse);
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

    List<ProductColor> colors = productColorRepository.findAllByProductId(product.getId());

    List<ProductComposition> compositions =
        productCompositionRepository.findAllByProductId(product.getId());

    List<ProductCare> careInstructions = productCareRepository.findAllByProductId(product.getId());

    return productMapper.toDetailsResponse(product);
  }

  @Transactional(readOnly = true)
  public Slice<ProductSummaryResponseDTO> findByCollectionId(Long collectionId, Pageable pageable) {
    if (!collectionRepository.existsById(collectionId)) {
      throw new EntityNotFoundException("Coleção", "id", collectionId);
    }

    return productRepository
        .findByCollectionId(collectionId, pageable)
        .map(productMapper::toSummaryResponse);
  }

  @Transactional
  public ProductDetailsResponseDTO create(ProductRequestDTO request) {
    if (productRepository.existsByNameAndStatus(request.name(), ProductStatus.ACTIVE)) {
      throw new EntityAlreadyExistsException("Produto", "nome", request.name());
    }
    if (productRepository.existsByNameAndStatus(request.name(), ProductStatus.ARCHIVED)) {
      throw new EntityAlreadyExistsException(
          "Já existe um produto com este nome, deseja reativá-lo?");
    }

    Product product = productMapper.toEntity(request);

    product.setSlug(generateUniqueSlug(request.name()));
    product.setCollection(resolveCollection(request.collectionId()));

    Product savedProduct = productRepository.save(product);

    return productMapper.toDetailsResponse(savedProduct);
  }

  @Transactional
  public void delete(Long id) {
    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produto", "id", id));
    productRepository.delete(product);
  }

  @Transactional
  public ProductDetailsResponseDTO partialUpdate(Long id, ProductPatchDTO request) {
    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produto", "id", id));

    request
        .name()
        .ifPresent(
            newName -> {
              if (!product.getName().equals(newName)) {
                if (productRepository.existsByName(newName)) {
                  throw new EntityAlreadyExistsException(
                      "Já existe um produto com este nome, deseja reativá-lo?");
                }
                product.setName(newName);
                product.setSlug(generateUniqueSlug(newName));
              }
            });
    request.description().ifPresent(product::setDescription);
    request.price().ifPresent(product::setPrice);
    request.category().ifPresent(product::setCategory);

    request
        .material()
        .ifPresent(
            newCompositions -> {
              product.getCompositions().clear();

              for (ProductCompositionRequestDTO compDTO : newCompositions) {
                ProductComposition newComposition = productMapper.toEntity(compDTO);

                product.addComposition(newComposition);
              }
            });

    Product savedProduct = productRepository.save(product);

    return productMapper.toDetailsResponse(savedProduct);
  }

  @Transactional
  public void archive(Long id) {
    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produto", "id", id));

    product.setStatus(ProductStatus.ARCHIVED);
  }

  private Collection resolveCollection(Long collectionId) {
    if (collectionId == null) return null;

    return collectionRepository
        .findById(collectionId)
        .orElseThrow(() -> new EntityNotFoundException("Coleção", "id", collectionId));
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
