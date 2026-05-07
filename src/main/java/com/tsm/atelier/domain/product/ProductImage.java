package com.tsm.atelier.domain.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_images")
@Getter
@Setter
public class ProductImage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String url;

  @Column(length = 100, nullable = false)
  private String fileName;

  @Column(nullable = false)
  private Integer displayOrder;

  @Column(nullable = false)
  private Boolean isCover = false;

  @ManyToOne
  @JoinColumn(name = "product_color_id", nullable = false)
  private ProductColor productColor;
}
