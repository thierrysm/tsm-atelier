package com.tm.tsm_atelier.core.repository;

import com.tm.tsm_atelier.core.entity.ClothingStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClothingStockRepository extends JpaRepository<ClothingStock, UUID> {
}
