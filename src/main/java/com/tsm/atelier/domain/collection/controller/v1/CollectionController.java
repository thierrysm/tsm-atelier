package com.tsm.atelier.domain.collection.controller.v1;

import com.tsm.atelier.domain.collection.CollectionService;
import com.tsm.atelier.domain.collection.CollectionStatus;
import com.tsm.atelier.domain.collection.dto.v1.request.CollectionPatchDTO;
import com.tsm.atelier.domain.collection.dto.v1.request.CollectionRequestDTO;
import com.tsm.atelier.domain.collection.dto.v1.response.CollectionResponseDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductSummaryResponseDTO;
import com.tsm.atelier.domain.product.service.ProductService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/collections")
public class CollectionController {
  private final CollectionService collectionService;
  private final ProductService productService;

  public CollectionController(CollectionService collectionService, ProductService productService) {
    this.collectionService = collectionService;
    this.productService = productService;
  }

  @GetMapping
  public ResponseEntity<List<CollectionResponseDTO>> findAll(
      @RequestParam(required = false) CollectionStatus status,
      @RequestParam(required = false) Boolean featured,
      @RequestParam(required = false) Boolean isNew,
      @RequestParam(required = false) Boolean showInHeader) {
    return ResponseEntity.ok(collectionService.findAll(status, featured, isNew, showInHeader));
  }

  @GetMapping("/{id}/products")
  public ResponseEntity<Slice<ProductSummaryResponseDTO>> findProductsByCollectionId(
      @PathVariable Long id,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "name") String sort) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
    return ResponseEntity.ok(productService.findByCollectionId(id, pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CollectionResponseDTO> findById(@PathVariable Long id) {
    return ResponseEntity.ok().body(collectionService.findById(id));
  }

  @GetMapping("/{slug}/slug")
  public ResponseEntity<CollectionResponseDTO> findBySlug(@PathVariable String slug) {
    return ResponseEntity.ok().body(collectionService.findBySlug(slug));
  }

  @PostMapping
  public ResponseEntity<CollectionResponseDTO> create(
      @Valid @RequestBody CollectionRequestDTO request) {
    CollectionResponseDTO response = collectionService.create(request);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(response.id())
            .toUri();
    return ResponseEntity.created(location).body(response);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<CollectionResponseDTO> partialUpdate(
      @PathVariable Long id, @Valid @RequestBody CollectionPatchDTO request) {
    CollectionResponseDTO collection = collectionService.partialUpdate(id, request);
    return ResponseEntity.ok().body(collection);
  }

  @PostMapping(path = "/{collectionId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<CollectionResponseDTO> upload(
      @PathVariable Long collectionId, @RequestParam MultipartFile file) {
    return ResponseEntity.status(201).body(collectionService.uploadImage(collectionId, file));
  }

  @PatchMapping("/{id}/activate")
  public ResponseEntity<CollectionResponseDTO> activateCollection(@PathVariable Long id) {
    return ResponseEntity.ok().body(collectionService.activateCollection(id));
  }

  @PatchMapping("/{id}/archive")
  public ResponseEntity<CollectionResponseDTO> archiveCollection(@PathVariable Long id) {
    return ResponseEntity.ok().body(collectionService.archiveCollection(id));
  }

  @PatchMapping("/{id}/unarchive")
  public ResponseEntity<CollectionResponseDTO> unarchiveCollection(@PathVariable Long id) {
    CollectionResponseDTO response = collectionService.unarchiveCollection(id);
    return ResponseEntity.ok().body(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCollection(@PathVariable Long id) {
    collectionService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
