package com.tsm.atelier.domain.collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "collection_images")
public class CollectionImage {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String url;

  @Column(length = 100)
  private String fileName;

  @OneToOne(mappedBy = "image")
  private Collection collection;
}
