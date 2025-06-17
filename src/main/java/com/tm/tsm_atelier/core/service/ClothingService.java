package com.tm.tsm_atelier.core.service;

import com.tm.tsm_atelier.core.dto.reponse.ClothingResponseDTO;
import com.tm.tsm_atelier.core.dto.request.ClothingRequestDTO;
import com.tm.tsm_atelier.core.repository.ClothingRepository;
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

    public ClothingResponseDTO addClothing(ClothingRequestDTO requestDTO) {

    }
}
