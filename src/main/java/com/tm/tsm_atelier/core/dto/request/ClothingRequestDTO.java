package com.tm.tsm_atelier.core.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tm.tsm_atelier.core.entity.enumaration.ClothingTypeEnum;
import com.tm.tsm_atelier.core.entity.enumaration.SizeEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ClothingRequestDTO {

    @NotBlank
    @Size(max = 100)
    @JsonProperty("name")
    private String name;

    @NotNull
    @Positive
    @JsonProperty("price")
    private BigDecimal price;

    @NotBlank
    @Size(max = 200)
    @JsonProperty("description")
    private String description;

    @NotNull
    @JsonProperty("type")
    private ClothingTypeEnum type;

    @NotEmpty
    @Valid
    @JsonProperty("stock")
    private List<ClothingStockRequestDTO> stock;
}
