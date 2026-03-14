package com.tsm.atelier.domain.product.controller.v1;

import com.tsm.atelier.domain.product.dto.v1.request.ProductPatchDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductDetailsResponseDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductSummaryResponseDTO;
import com.tsm.atelier.domain.product.service.ProductService;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;

  @GetMapping
  public ResponseEntity<Slice<ProductSummaryResponseDTO>> findAll(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "name") String sort) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
    return ResponseEntity.ok(productService.findAllSummary(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDetailsResponseDTO> findProductDetailsById(@PathVariable Long id) {
    ProductDetailsResponseDTO product = productService.findProductDetailsById(id);
    return ResponseEntity.ok(product);
  }

  @GetMapping("/slug/{slug}")
  public ResponseEntity<ProductDetailsResponseDTO> findProductDetailsBySlug(
      @PathVariable String slug) {
    ProductDetailsResponseDTO product = productService.findProductDetailsBySlug(slug);
    return ResponseEntity.ok(product);
  }

  @PostMapping
  public ResponseEntity<ProductDetailsResponseDTO> create(
      @RequestBody @Valid ProductRequestDTO request) {
    ProductDetailsResponseDTO response = productService.create(request);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(response.id())
            .toUri();
    return ResponseEntity.created(location).body(response);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ProductDetailsResponseDTO> partialUpdate(
      @PathVariable Long id, @Valid @RequestBody ProductPatchDTO request) {
    return ResponseEntity.ok(productService.partialUpdate(id, request));
  }

  @PatchMapping("/{id}/publish")
  public ResponseEntity<Void> publish(@PathVariable Long id) {
    productService.publish(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    productService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{id}/archive")
  public ResponseEntity<Void> archive(@PathVariable Long id) {
    productService.archive(id);
    return ResponseEntity.noContent().build();
  }
}
