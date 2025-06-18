package com.tm.tsm_atelier.core.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tm.tsm_atelier.core.entity.enumaration.SizeEnum;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClothingStockRequestDTO {
    @NotNull
    @JsonProperty("size")
    private SizeEnum size;

    @PositiveOrZero
    @JsonProperty("quantity")
    private int quantity;

    @NotBlank
    @Size(max = 100)
    @JsonProperty("color")
    private String color;

    @NotEmpty
    @JsonProperty("imagesUrl")
    private List<String> imagesUrl;
}
