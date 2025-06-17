package com.tm.tsm_atelier.core.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tm.tsm_atelier.core.entity.enumaration.SizeEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClothingStockRequestDTO {
    @NotNull
    @JsonProperty("size")
    private SizeEnum size;
    @PositiveOrZero
    @JsonProperty("size")
    private int quantity;
}
