package com.tsm.atelier.domain.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_cares")
@Getter
@Setter
public class ProductCare {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(length = 100, nullable = false)
  private String instruction;

  @Column(nullable = false)
  private Integer displayOrder;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;
}
