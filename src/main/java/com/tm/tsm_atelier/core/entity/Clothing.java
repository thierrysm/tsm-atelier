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
public class Clothing {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "color", nullable = false, length = 100)
    private String color;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Column(name = "images_url", nullable = false)
    private List<String> imagesUrl = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 11)
    private ClothingTypeEnum type;

    @OneToMany(mappedBy = "clothing", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ClothingStock> stockItems = new ArrayList<>();

    public void addStock(SizeEnum size, int quantity) {
        ClothingStock newStock = new ClothingStock(this, size, quantity);
        this.stockItems.add(newStock);
    }
}