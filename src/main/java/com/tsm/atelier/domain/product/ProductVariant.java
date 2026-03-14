package com.tsm.atelier.domain.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "product_variants",
    uniqueConstraints = @UniqueConstraint(columnNames = {"product_color_id", "size"}))
@Getter
@Setter
public class ProductVariant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "product_color_id", nullable = false)
  private ProductColor productColor;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ProductSize size;

  @Column(nullable = false)
  private Integer stock;

  @Version private Integer version;
}
