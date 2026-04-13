package com.tsm.atelier.domain.assets;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaAssetRepository extends JpaRepository<MediaAsset, Long> {
  Optional<MediaAsset> findByPosition(AssetPosition position);

  List<MediaAsset> findAllByPositionIn(List<AssetPosition> positions);
}
