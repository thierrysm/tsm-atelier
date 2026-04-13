package com.tsm.atelier.domain.assets;

import com.tsm.atelier.domain.assets.dto.v1.reponse.MediaAssetResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MediaAssetMapper {
  MediaAssetResponseDTO toResponse(MediaAsset mediaAsset);
}
