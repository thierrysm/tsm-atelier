package com.tsm.atelier.domain.product.controller.v1;

import com.tsm.atelier.domain.product.dto.v1.request.ProductColorPatchDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductColorRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductColorResponseDTO;
import com.tsm.atelier.domain.product.service.ProductColorService;
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
@RequestMapping("/api/v1/products/{productId}/colors")
@RequiredArgsConstructor
public class ProductColorController {
  private final ProductColorService productColorService;

  @PostMapping
  public ResponseEntity<ProductColorResponseDTO> addColor(
      @PathVariable Long productId, @Valid @RequestBody ProductColorRequestDTO request) {
    ProductColorResponseDTO response = productColorService.addColorToProduct(productId, request);

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(response.id())
            .toUri();

    return ResponseEntity.created(location).body(response);
  }

  @PatchMapping("/{productColorId}")
  public ResponseEntity<ProductColorResponseDTO> updateColor(
      @PathVariable Long productColorId,
      @PathVariable Long productId,
      @Valid @RequestBody ProductColorPatchDTO request) {
    ProductColorResponseDTO response =
        productColorService.updateColor(productColorId, productId, request);

    return ResponseEntity.ok().body(response);
  }

  @DeleteMapping("/{productColorId}")
  public ResponseEntity<Void> delete(
      @PathVariable("productId") Long productId,
      @PathVariable("productColorId") Long productColorId) {

    productColorService.delete(productColorId, productId);

    return ResponseEntity.noContent().build();
  }
}
