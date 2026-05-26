package com.tsm.atelier.domain.collection;

import com.tsm.atelier.domain.product.Product;
import com.tsm.atelier.domain.product.TargetAudience;
import com.tsm.atelier.shared.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "collections")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Collection extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 100, nullable = false, unique = true)
  private String name;

  @Column(length = 120, nullable = false, unique = true)
  private String slug;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String description;

  @Column(columnDefinition = "TEXT")
  private String imageUrl;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 15)
  private CollectionStatus status = CollectionStatus.DRAFT;

  @Enumerated(EnumType.STRING)
  @Column(name = "target_audience", nullable = false, length = 20)
  private TargetAudience targetAudience = TargetAudience.UNISEX;

  @Column(nullable = false)
  private Boolean featured = false;

  @Column(nullable = false)
  private Boolean showInHeader = false;

  @Column(nullable = false)
  private Boolean isNew = false;

  @Column(nullable = false)
  private Integer displayOrder = 0;

  @Column private Instant disabledAt;

  @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL)
  private List<Product> products = new ArrayList<>();
}
