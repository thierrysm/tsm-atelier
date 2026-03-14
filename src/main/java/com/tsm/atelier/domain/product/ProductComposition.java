package com.tsm.atelier.domain.product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_compositions")
@Getter
@Setter
public class ProductComposition {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private CompositionType type;

  @OneToMany(mappedBy = "composition", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CompositionMaterial> materials = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  public void addMaterial(CompositionMaterial material) {
    this.materials.add(material);
    material.setComposition(this);
  }
}
