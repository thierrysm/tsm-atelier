package com.tsm.atelier.domain.product;

import com.tsm.atelier.domain.collection.Collection;
import com.tsm.atelier.shared.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE products SET active = false, disabled_at = NOW() WHERE id = ?")
@SQLRestriction("active = true")
public class Product extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(unique = true, nullable = false, length = 150)
  private String name;

  @Column(unique = true, nullable = false, length = 200)
  private String alias;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String description;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal price;

  @Column(nullable = false, length = 100)
  private String material;

  @Column(nullable = false)
  private boolean active = true;

  @ManyToOne
  @JoinColumn(name = "collection_id")
  private Collection collection;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("displayOrder ASC")
  private List<ProductCare> careInstructions = new ArrayList<>();

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProductColor> colors = new ArrayList<>();
}
