package com.tsm.atelier.domain.product.controller.v1;

import com.tsm.atelier.domain.product.dto.v1.response.ProductImageResponseDTO;
import com.tsm.atelier.domain.product.service.ProductImageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
  public ResponseEntity<List<ProductImageResponseDTO>> upload(
      @PathVariable Long productId,
      @PathVariable Long colorId,
      @RequestParam("file") List<MultipartFile> files,
      @RequestParam(defaultValue = "false") Boolean isCover) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(productImageService.upload(productId, colorId, files, isCover));
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
