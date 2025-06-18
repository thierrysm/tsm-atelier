package com.tm.tsm_atelier.core.entity;

import com.tm.tsm_atelier.core.entity.enumaration.SizeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clothing_stock_tb")
public class ClothingStockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "clothing_id", nullable = false)
    private ClothingEntity clothing;

    @Enumerated(EnumType.STRING)
    @Column(name = "size", nullable = false, length = 2)
    private SizeEnum size;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "color", nullable = false, length = 100)
    private String color;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "clothing_stock_images_url_tb", joinColumns = @JoinColumn(name = "clothing_stock_id"))
    @Column(name = "image_url", nullable = false)
    private List<String> imagesUrl = new ArrayList<>();

    public ClothingStockEntity(ClothingEntity clothing, String color, SizeEnum size, int quantity, List<String> imagesUrl) {
        this.clothing = clothing;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.imagesUrl = imagesUrl;
    }
}
