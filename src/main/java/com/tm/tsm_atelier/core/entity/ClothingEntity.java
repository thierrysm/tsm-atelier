package com.tm.tsm_atelier.core.entity;

import com.tm.tsm_atelier.core.entity.enumaration.ClothingTypeEnum;
import com.tm.tsm_atelier.core.entity.enumaration.SizeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clothing_tb")
public class ClothingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 11)
    private ClothingTypeEnum type;

    @OneToMany(mappedBy = "clothing", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ClothingStockEntity> stockItems = new ArrayList<>();

    public void addStock(String color, SizeEnum size, int quantity, List<String> imagesUrl) {
        ClothingStockEntity newStock = new ClothingStockEntity(this, color, size, quantity, imagesUrl);
        this.stockItems.add(newStock);
    }
}