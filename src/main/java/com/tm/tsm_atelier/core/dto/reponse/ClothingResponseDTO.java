package com.tm.tsm_atelier.core.dto.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ClothingResponseDTO {
    @JsonProperty("name")
    private UUID id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("stockItems")
    private List<ClothingStockResponseDTO> stockItems;
}
