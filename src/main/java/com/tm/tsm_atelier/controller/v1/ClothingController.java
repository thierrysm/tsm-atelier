package com.tm.tsm_atelier.controller.v1;

import com.tm.tsm_atelier.core.dto.reponse.ClothingResponseDTO;
import com.tm.tsm_atelier.core.dto.request.ClothingRequestDTO;
import com.tm.tsm_atelier.core.service.ClothingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clothing")
public class ClothingController {
    private ClothingService clothingService;

    public ClothingController(ClothingService clothingService) {
        this.clothingService = clothingService;
    }

    @PostMapping
    public ResponseEntity<ClothingResponseDTO> addClothing(@Valid @RequestBody ClothingRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clothingService.addClothing(requestDTO));
    }

    @GetMapping
    public String tt() {
        return "GETTTTTT";
    }
}
