package com.tsm.atelier.domain.collection;

import com.tsm.atelier.domain.collection.dto.v1.request.CollectionPatchDTO;
import com.tsm.atelier.domain.collection.dto.v1.request.CollectionRequestDTO;
import com.tsm.atelier.domain.collection.dto.v1.response.CollectionResponseDTO;
import com.tsm.atelier.domain.product.repository.ProductRepository;
import com.tsm.atelier.exception.BusinessException;
import com.tsm.atelier.exception.EntityAlreadyExistsException;
import com.tsm.atelier.exception.EntityNotFoundException;
import com.tsm.atelier.shared.UploadResult;
import com.tsm.atelier.shared.image.ImageFolder;
import com.tsm.atelier.shared.image.ImageService;
import com.tsm.atelier.shared.util.SlugUtils;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class CollectionService {
  private CollectionRepository collectionRepository;
  private CollectionMapper collectionMapper;
  private ImageService imageService;
  private ProductRepository productRepository;

  @Transactional(readOnly = true)
  public List<CollectionResponseDTO> findAll(
      CollectionStatus status, Boolean featured, Boolean isNew, Boolean showInHeader) {
    return collectionRepository.findWithFilters(status, featured, isNew, showInHeader).stream()
        .map(collectionMapper::toResponse)
        .toList();
  }

  @Transactional(readOnly = true)
  public CollectionResponseDTO findById(Long id) {
    Collection collection =
        collectionRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Coleção", "id", id));

    return collectionMapper.toResponse(collection);
  }

  @Transactional(readOnly = true)
  public CollectionResponseDTO findBySlug(String slug) {
    Collection collection =
        collectionRepository
            .findBySlug(slug)
            .orElseThrow(() -> new EntityNotFoundException("Coleção", "slug", slug));

    return collectionMapper.toResponse(collection);
  }

  @Transactional
  public CollectionResponseDTO create(CollectionRequestDTO request) {
    if (collectionRepository.existsByName(request.name())) {
      throw new EntityAlreadyExistsException("Collection", "name", request.name());
    }
    Collection collection = collectionMapper.toEntity(request);
    collection.setSlug(generateUniqueSlug(request.name()));

    return collectionMapper.toResponse(collectionRepository.save(collection));
  }

  @Transactional
  public CollectionResponseDTO partialUpdate(Long id, CollectionPatchDTO request) {
    Collection collection =
        collectionRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Collection", "id", id));

    request
        .name()
        .ifPresent(
            newName -> {
              if (!collection.getName().equals(newName)) {
                if (collectionRepository.existsByName(newName)) {
                  throw new EntityAlreadyExistsException("Product", "name", newName);
                }
                collection.setName(newName);
                collection.setSlug(generateUniqueSlug(newName));
              }
            });
    request.description().ifPresent(collection::setDescription);
    request.status().ifPresent(collection::setStatus);
    request.featured().ifPresent(collection::setFeatured);
    request.showInHeader().ifPresent(collection::setShowInHeader);
    request.isNew().ifPresent(collection::setIsNew);
    request.displayOrder().ifPresent(collection::setDisplayOrder);

    return collectionMapper.toResponse(collectionRepository.save(collection));
  }

  @Transactional
  public CollectionResponseDTO uploadImage(Long collectionId, MultipartFile file) {
    Collection collection =
        collectionRepository
            .findById(collectionId)
            .orElseThrow(() -> new EntityNotFoundException("Collection", "id", collectionId));

    if (collection.getImageUrl() != null) {
      imageService.delete(collection.getImageUrl());
    }

    UploadResult result = imageService.upload(file, ImageFolder.COLLECTIONS);
    collection.setImageUrl(result.url());
    return collectionMapper.toResponse(collectionRepository.save(collection));
  }

  @Transactional
  public void delete(Long collectionId) {
    Collection collection =
        collectionRepository
            .findById(collectionId)
            .orElseThrow(() -> new EntityNotFoundException("Collection", "id", collectionId));

    if (!collection.getProducts().isEmpty()) {
      throw new BusinessException(
          "Não é possível excluir fisicamente uma coleção que ainda possui produtos vinculados. Arquive-a ou remova os produtos primeiro.");
    }

    collectionRepository.delete(collection);
  }

  @Transactional
  public CollectionResponseDTO activateCollection(Long id) {
    Collection collection =
        collectionRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Coleção", "id", id));

    if (collection.getStatus().equals(CollectionStatus.ACTIVE)) {
      throw new BusinessException("Coleção já está ativada");
    }

    if (collection.getStatus().equals(CollectionStatus.ARCHIVED)) {
      throw new BusinessException("Coleção está arquivada");
    }

    if (!StringUtils.hasText(collection.getImageUrl())) {
      throw new BusinessException("Coleção não pode ser ativada sem uma imagem");
    }

    collection.setStatus(CollectionStatus.ACTIVE);

    return collectionMapper.toResponse(collectionRepository.save(collection));
  }

  @Transactional
  public CollectionResponseDTO archiveCollection(Long id) {
    Collection collection =
        collectionRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Coleção", "id", id));

    if (collection.getStatus().equals(CollectionStatus.ARCHIVED)) {
      throw new BusinessException("Coleção já está arquivada");
    }

    Instant momentOfArchiving = Instant.now();

    collection.setStatus(CollectionStatus.ARCHIVED);
    collection.setDisabledAt(momentOfArchiving);
    productRepository.archiveAllByCollectionId(collection.getId(), momentOfArchiving);

    return collectionMapper.toResponse(collectionRepository.save(collection));
  }

  @Transactional
  public CollectionResponseDTO unarchiveCollection(Long id) {
    Collection collection =
        collectionRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Coleção", "id", id));

    if (!collection.getStatus().equals(CollectionStatus.ARCHIVED)) {
      throw new BusinessException("A coleção não está arquivada.");
    }

    collection.setStatus(CollectionStatus.ACTIVE);
    collection.setDisabledAt(null);

    return collectionMapper.toResponse(collectionRepository.save(collection));
  }

  private String generateUniqueSlug(String name) {
    String base = SlugUtils.generate(name);

    if (!collectionRepository.existsBySlug(base)) return base;

    int count = 1;
    String alias;
    do {
      alias = base + "-" + count++;
    } while (collectionRepository.existsBySlug(alias));

    return alias;
  }
}
