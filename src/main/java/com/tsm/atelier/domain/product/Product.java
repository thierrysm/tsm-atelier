package com.tsm.atelier.domain.product;

import com.tsm.atelier.domain.collection.Collection;
import com.tsm.atelier.shared.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE products SET status = 'ARCHIVED', disabled_at = NOW() WHERE id = ?")
public class Product extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 150)
  private String name;

  @Column(unique = true, nullable = false, length = 200)
  private String slug;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String description;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal price;

  @Column(name = "promotional_price", precision = 10, scale = 2)
  private BigDecimal promotionalPrice;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  @BatchSize(size = 50)
  private List<ProductComposition> compositions = new ArrayList<>();

  @Column(nullable = false, length = 20)
  @Enumerated(EnumType.STRING)
  private ProductCategory category;

  @Enumerated(EnumType.STRING)
  @Column(name = "target_audience", nullable = false, length = 20)
  private TargetAudience targetAudience;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private ProductStatus status = ProductStatus.DRAFT;

  @Column private Instant disabledAt;

  @ManyToOne
  @JoinColumn(name = "collection_id")
  private Collection collection;

  @ElementCollection
  @CollectionTable(name = "product_cares", joinColumns = @JoinColumn(name = "product_id"))
  @Column(name = "care_instruction", nullable = false)
  private List<String> careInstructions = new ArrayList<>();

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  @BatchSize(size = 50)
  private List<ProductColor> colors = new ArrayList<>();

  @Version private Integer version;

  public void addComposition(ProductComposition composition) {
    this.compositions.add(composition);
    composition.setProduct(this);
  }
}
