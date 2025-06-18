package com.tm.tsm_atelier.core.dto.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tm.tsm_atelier.core.entity.enumaration.SizeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ClothingStockResponseDTO {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("size")
    private SizeEnum size;
    @JsonProperty("quantity")
    private int quantity;
    @JsonProperty("color")
    private String color;
}
