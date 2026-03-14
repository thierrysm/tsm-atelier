package com.tsm.atelier.domain.product.controller.v1;

import com.tsm.atelier.domain.product.ProductService;
import com.tsm.atelier.domain.product.dto.v1.request.ProductPatchDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductDetailsResponseDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductSummaryResponseDTO;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public ResponseEntity<Page<ProductSummaryResponseDTO>> findAll(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "name") String sort) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
    return ResponseEntity.ok(productService.findAllSummary(pageable));
  }

  @GetMapping("/{alias}")
  public ResponseEntity<ProductDetailsResponseDTO> findByAlias(@PathVariable String alias) {
    return ResponseEntity.ok(productService.findByAlias(alias));
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

  @DeleteMapping("/{id}")
  public ResponseEntity<ProductDetailsResponseDTO> delete(@PathVariable UUID id) {
    productService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ProductDetailsResponseDTO> partialUpdate(
      @PathVariable UUID id, @Valid @RequestBody ProductPatchDTO request) {
    return ResponseEntity.ok(productService.partialUpdate(id, request));
  }
}
