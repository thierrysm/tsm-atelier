package com.tm.tsm_atelier.core.repository;

import com.tm.tsm_atelier.core.entity.Clothing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClothingRepository extends JpaRepository<Clothing, UUID> {
}
