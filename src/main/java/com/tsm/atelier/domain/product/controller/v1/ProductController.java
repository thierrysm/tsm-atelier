package com.tsm.atelier.domain.product.controller.v1;

import com.tsm.atelier.domain.product.dto.v1.request.ProductFilterDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductPatchDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductDetailsResponseDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductSummaryResponseDTO;
import com.tsm.atelier.domain.product.service.ProductService;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;

  @GetMapping
  public ResponseEntity<Page<ProductSummaryResponseDTO>> findAll(
      @ModelAttribute ProductFilterDTO filter, @PageableDefault(sort = "name") Pageable pageable) {
    return ResponseEntity.ok(productService.findAllFiltered(filter, pageable));
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
  public ResponseEntity<ProductDetailsResponseDTO> publish(@PathVariable Long id) {
    return ResponseEntity.ok().body(productService.publish(id));
  }

  @PatchMapping("/{id}/archive")
  public ResponseEntity<ProductDetailsResponseDTO> archive(@PathVariable Long id) {
    return ResponseEntity.ok().body(productService.archive(id));
  }
}
