package com.tsm.atelier.domain.assets;

import com.tsm.atelier.domain.assets.dto.v1.reponse.MediaAssetResponseDTO;
import com.tsm.atelier.exception.EntityNotFoundException;
import com.tsm.atelier.shared.UploadResult;
import com.tsm.atelier.shared.image.ImageFolder;
import com.tsm.atelier.shared.image.ImageService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MediaAssetService {

  private final MediaAssetRepository mediaAssetRepository;
  private final ImageService imageService;
  private final MediaAssetMapper mediaAssetMapper;

  @Transactional(readOnly = true)
  public MediaAssetResponseDTO findByPosition(AssetPosition position) {
    return mediaAssetRepository
        .findByPosition(position)
        .map(mediaAssetMapper::toResponse)
        .orElseThrow(() -> new EntityNotFoundException("MediaAsset", "position", position));
  }

  @Transactional(readOnly = true)
  public List<MediaAssetResponseDTO> findAllByPositions(List<AssetPosition> positions) {

    return mediaAssetRepository.findAllByPositionIn(positions).stream()
        .map(mediaAssetMapper::toResponse)
        .toList();
  }

  @Transactional
  public MediaAssetResponseDTO upload(
      AssetPosition position,
      MultipartFile file,
      String altText,
      String linkUrl,
      String title,
      String subtitle,
      String buttonText) {

    Optional<MediaAsset> existing = mediaAssetRepository.findByPosition(position);
    String oldUrl = existing.map(MediaAsset::getUrl).orElse(null);

    UploadResult result = imageService.upload(file, ImageFolder.ASSETS);

    MediaAsset asset = existing.orElse(new MediaAsset());
    asset.setPosition(position);
    asset.setUrl(result.url());
    asset.setFileName(result.fileName());
    asset.setAltText(altText);
    asset.setLinkUrl(linkUrl);
    asset.setTitle(title);
    asset.setSubtitle(subtitle);
    asset.setButtonText(buttonText);

    MediaAssetResponseDTO response = mediaAssetMapper.toResponse(mediaAssetRepository.save(asset));

    if (oldUrl != null) {
      imageService.delete(oldUrl);
    }

    return response;
  }

  @Transactional
  public void delete(AssetPosition position) {
    MediaAsset asset =
        mediaAssetRepository
            .findByPosition(position)
            .orElseThrow(() -> new EntityNotFoundException("MediaAsset", "position", position));

    imageService.delete(asset.getUrl());
    mediaAssetRepository.delete(asset);
  }
}
