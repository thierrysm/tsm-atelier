package com.tsm.atelier.domain.product;

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
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_colors")
@Getter
@Setter
@EqualsAndHashCode
public class ProductColor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 50)
  private String name;

  @Column(length = 7)
  private String hexCode;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @OneToMany(mappedBy = "productColor", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("displayOrder ASC")
  private Set<ProductImage> images = new LinkedHashSet<>();

  @OneToMany(mappedBy = "productColor", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<ProductVariant> variants = new HashSet<>();
}
