package com.tsm.atelier.domain.assets;

import com.tsm.atelier.shared.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "media_assets")
@Getter
@Setter
public class MediaAsset extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, unique = true, length = 30)
  private AssetPosition position;

  @Column(nullable = false)
  private String url;

  @Column(nullable = false, length = 60)
  private String fileName;

  @Column(length = 60)
  private String altText;

  @Column(length = 60)
  private String linkUrl;
}
