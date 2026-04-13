package com.tsm.atelier.domain.assets.dto.v1.reponse;

import com.tsm.atelier.domain.assets.AssetPosition;

public record MediaAssetResponseDTO(
    Long id,
    AssetPosition position,
    String url,
    String fileName,
    String altText,
    String linkUrl,
    Boolean active) {}
