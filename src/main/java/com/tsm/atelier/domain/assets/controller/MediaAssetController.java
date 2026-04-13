package com.tsm.atelier.domain.assets.controller;

import com.tsm.atelier.domain.assets.AssetPosition;
import com.tsm.atelier.domain.assets.MediaAssetService;
import com.tsm.atelier.domain.assets.dto.v1.reponse.MediaAssetResponseDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/media-assets")
@RequiredArgsConstructor
public class MediaAssetController {

  private final MediaAssetService mediaAssetService;

  @GetMapping("/{position}")
  public ResponseEntity<MediaAssetResponseDTO> findByPosition(
      @PathVariable AssetPosition position) {
    return ResponseEntity.ok(mediaAssetService.findByPosition(position));
  }

  @GetMapping
  public ResponseEntity<List<MediaAssetResponseDTO>> findAllByPositions(
      @RequestParam List<AssetPosition> positions) {
    return ResponseEntity.ok(mediaAssetService.findAllByPositions(positions));
  }

  @PutMapping(value = "/{position}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<MediaAssetResponseDTO> upload(
      @PathVariable AssetPosition position,
      @RequestParam MultipartFile file,
      @RequestParam(required = false) String altText,
      @RequestParam(required = false) String linkUrl) {
    return ResponseEntity.ok(mediaAssetService.upload(position, file, altText, linkUrl));
  }

  @DeleteMapping("/{position}")
  public ResponseEntity<Void> delete(@PathVariable AssetPosition position) {
    mediaAssetService.delete(position);
    return ResponseEntity.noContent().build();
  }
}
