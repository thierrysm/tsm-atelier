package com.tsm.atelier.domain.collection;

import com.tsm.atelier.domain.product.Product;
import com.tsm.atelier.shared.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(length = 100, nullable = false)
  private String name;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String description;

  @Column(nullable = false)
  private Integer year;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "image_id")
  private CollectionImage image;

  @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL)
  private List<Product> products = new ArrayList<>();
}
