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

    @NotBlank(message = "O nome da peça é obrigatório.")
    @Size(max = 100, message = "O nome não pode exceder 100 caracteres.")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "O preço é obrigatório.")
    @Positive(message = "O preço deve ser um valor positivo.")
    @JsonProperty("price")
    private BigDecimal price;

    @NotBlank(message = "A cor é obrigatória.")
    @Size(max = 100, message = "A cor não pode exceder 100 caracteres.")
    @JsonProperty("color")
    private String color;

    @NotBlank(message = "A descrição é obrigatória.")
    @Size(max = 200, message = "A descrição não pode exceder 200 caracteres.")
    @JsonProperty("description")
    private String description;

    @NotEmpty(message = "É necessário fornecer ao menos uma URL de imagem.")
    @JsonProperty("imagesUrl")
    private List<String> imagesUrl;

    @NotNull(message = "O tamanho (size) é obrigatório.")
    @JsonProperty("size")
    private SizeEnum size;

    @NotNull(message = "O tipo (type) da peça é obrigatório.")
    @JsonProperty("type")
    private ClothingTypeEnum type;

    @NotEmpty
    @Valid
    @JsonProperty("stock")
    private List<ClothingStockRequestDTO> stock;
}
