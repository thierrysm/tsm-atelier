package com.tsm.atelier.domain.product;

import com.tsm.atelier.domain.collection.Collection;
import com.tsm.atelier.domain.collection.CollectionRepository;
import com.tsm.atelier.domain.product.dto.v1.request.ProductPatchDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductDetailsResponseDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductSummaryResponseDTO;
import com.tsm.atelier.exception.ProductAlreadyExistsException;
import com.tsm.atelier.exception.ProductNotFoundException;
import com.tsm.atelier.exception.ResourceNotFoundException;
import com.tsm.atelier.shared.util.AliasUtils;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final CollectionRepository collectionRepository;

  @Transactional(readOnly = true)
  public Page<ProductSummaryResponseDTO> findAllSummary(Pageable pageable) {
    return productRepository.findAll(pageable).map(productMapper::toSummaryResponse);
  }

  @Transactional(readOnly = true)
  public ProductDetailsResponseDTO findByAlias(String alias) {
    Product product =
        productRepository
            .findByAlias(alias)
            .orElseThrow(() -> new ProductNotFoundException("alias", alias));

    return productMapper.toDetailsResponse(product);
  }

  @Transactional
  public ProductDetailsResponseDTO create(ProductRequestDTO request) {
    if (productRepository.existsByName(request.name())) {
      throw new ProductAlreadyExistsException(request.name());
    }

    Product product = productMapper.toEntity(request);

    product.setAlias(generateUniqueAlias(request.name()));
    product.setCollection(resolveCollection(request.collectionId()));

    return productMapper.toDetailsResponse(productRepository.save(product));
  }

  @Transactional
  public void delete(UUID id) {
    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new ProductNotFoundException("id", id.toString()));
    productRepository.delete(product);
  }

  @Transactional
  public ProductDetailsResponseDTO partialUpdate(UUID id, ProductPatchDTO request) {
    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new ProductNotFoundException("id", id.toString()));

    request
        .name()
        .ifPresent(
            newName -> {
              if (!product.getName().equals(newName)) {
                if (productRepository.existsByName(newName)) {
                  throw new ProductAlreadyExistsException(newName);
                }
                product.setName(newName);
                product.setAlias(generateUniqueAlias(newName));
              }
            });

    request.description().ifPresent(product::setDescription);
    request.price().ifPresent(product::setPrice);
    request.material().ifPresent(product::setMaterial);

    return productMapper.toDetailsResponse(productRepository.save(product));
  }

  private Collection resolveCollection(UUID collectionId) {
    if (collectionId == null) return null;

    try {
      return collectionRepository.getReferenceById(collectionId);
    } catch (jakarta.persistence.EntityNotFoundException e) {
      throw new ResourceNotFoundException("Collection not found with id: " + collectionId);
    }
  }

  private String generateUniqueAlias(String name) {
    String base = AliasUtils.generate(name);

    if (!productRepository.existsByAlias(base)) return base;

    int count = 1;
    String alias;
    do {
      alias = base + "-" + count++;
    } while (productRepository.existsByAlias(alias));

    return alias;
  }
}
