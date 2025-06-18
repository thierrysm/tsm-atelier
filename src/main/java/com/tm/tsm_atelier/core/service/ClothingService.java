package com.tm.tsm_atelier.core.service;

import com.tm.tsm_atelier.core.dto.reponse.ClothingResponseDTO;
import com.tm.tsm_atelier.core.dto.request.ClothingRequestDTO;
import com.tm.tsm_atelier.core.dto.request.ClothingStockRequestDTO;
import com.tm.tsm_atelier.core.entity.ClothingEntity;
import com.tm.tsm_atelier.core.repository.ClothingRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ClothingService {
    private ClothingRepository clothingRepository;
    private ModelMapper modelMapper;

    public ClothingService(ClothingRepository clothingRepository, ModelMapper modelMapper) {
        this.clothingRepository = clothingRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public ClothingResponseDTO addClothing(ClothingRequestDTO requestDTO) {
        ClothingEntity clothingEntity = modelMapper.map(requestDTO, ClothingEntity.class);

        for (ClothingStockRequestDTO stockDTO : requestDTO.getStock()) {
            clothingEntity.addStock(
                    stockDTO.getColor(),
                    stockDTO.getSize(),
                    stockDTO.getQuantity(),
                    stockDTO.getImagesUrl()
            );
        }

        ClothingEntity savedClothing = clothingRepository.save(clothingEntity);

        return modelMapper.map(savedClothing, ClothingResponseDTO.class);
    }
}
