package com.tsm.atelier.domain.product.controller.v1;

import com.tsm.atelier.domain.product.dto.v1.response.ProductImageResponseDTO;
import com.tsm.atelier.domain.product.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/products/{productId}/colors/{colorId}/images")
@RequiredArgsConstructor
public class ProductImageController {
  private final ProductImageService productImageService;

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<ProductImageResponseDTO> upload(
      @PathVariable Long productId,
      @PathVariable Long colorId,
      @RequestParam MultipartFile file,
      @RequestParam(defaultValue = "false") Boolean isCover) {
    return ResponseEntity.status(201)
        .body(productImageService.upload(productId, colorId, file, isCover));
  }

  @PatchMapping("/{imageId}/cover")
  public ResponseEntity<Void> setCover(
      @PathVariable Long productId, @PathVariable Long colorId, @PathVariable Long imageId) {
    productImageService.setCover(productId, colorId, imageId);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{imageId}")
  public ResponseEntity<Void> delete(
      @PathVariable Long productId, @PathVariable Long colorId, @PathVariable Long imageId) {
    productImageService.delete(productId, colorId, imageId);
    return ResponseEntity.noContent().build();
  }
}
