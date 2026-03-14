package com.tsm.atelier.domain.product.controller.v1;

import com.tsm.atelier.domain.product.dto.v1.request.ProductVariantPatchDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductVariantRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductVariantResponseDTO;
import com.tsm.atelier.domain.product.service.ProductVariantService;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/products/{productId}/colors/{colorId}/variants")
@RequiredArgsConstructor
public class ProductVariantController {
  private final ProductVariantService productVariantService;

  @PostMapping
  public ResponseEntity<ProductVariantResponseDTO> addVariant(
      @PathVariable Long productId,
      @PathVariable Long colorId,
      @Valid @RequestBody ProductVariantRequestDTO request) {
    ProductVariantResponseDTO response =
        productVariantService.addVariant(productId, colorId, request);

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(response.id())
            .toUri();

    return ResponseEntity.created(location).body(response);
  }

  @PatchMapping("/{variantId}/stock")
  public ResponseEntity<ProductVariantResponseDTO> updateStock(
      @PathVariable Long productId,
      @PathVariable Long colorId,
      @PathVariable Long variantId,
      @Valid @RequestBody ProductVariantPatchDTO request) {
    return ResponseEntity.ok(
        productVariantService.updateStock(productId, colorId, variantId, request));
  }

  @DeleteMapping("/{variantId}")
  public ResponseEntity<Void> delete(
      @PathVariable Long productId, @PathVariable Long colorId, @PathVariable Long variantId) {
    productVariantService.delete(productId, colorId, variantId);

    return ResponseEntity.noContent().build();
  }
}
