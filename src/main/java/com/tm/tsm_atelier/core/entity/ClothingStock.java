package com.tm.tsm_atelier.core.entity;

import com.tm.tsm_atelier.core.entity.enumaration.SizeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clothing_stock_tb")
public class ClothingStock {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "clothing_id", nullable = false)
    private Clothing clothing;

    @Enumerated(EnumType.STRING)
    @Column(name = "size", nullable = false, length = 2)
    private SizeEnum size;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public ClothingStock(Clothing clothing, SizeEnum size, int quantity) {
        this.clothing = clothing;
        this.size = size;
        this.quantity = quantity;
    }
}
