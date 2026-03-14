package com.tsm.atelier.domain.product;

import com.tsm.atelier.domain.product.dto.v1.request.ProductCareRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductColorRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductImageRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductVariantRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ColorSummaryResponseDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductCareResponseDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductDetailsResponseDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductSummaryResponseDTO;
import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "collection", ignore = true)
  Product toEntity(ProductRequestDTO request);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "product", ignore = true)
  ProductCare toEntity(ProductCareRequestDTO request);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "product", ignore = true)
  ProductColor toEntity(ProductColorRequestDTO request);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "productColor", ignore = true)
  ProductImage toEntity(ProductImageRequestDTO request);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "productColor", ignore = true)
  @Mapping(target = "version", ignore = true)
  ProductVariant toEntity(ProductVariantRequestDTO request);

  @Mapping(target = "collectionName", source = "collection.name")
  ProductDetailsResponseDTO toDetailsResponse(Product product);

  @Mapping(target = "allowed", constant = "true")
  ProductCareResponseDTO toCareResponse(ProductCare care); // sem constant hardcoded

  @AfterMapping
  default void setBackReferences(@MappingTarget Product product) {
    if (product.getCareInstructions() != null) {
      product.getCareInstructions().forEach(care -> care.setProduct(product));
    }
    if (product.getColors() != null) {
      product
          .getColors()
          .forEach(
              color -> {
                color.setProduct(product);
                if (color.getImages() != null) {
                  color.getImages().forEach(image -> image.setProductColor(color));
                }
                if (color.getVariants() != null) {
                  color.getVariants().forEach(variant -> variant.setProductColor(color));
                }
              });
    }
  }

  default ProductSummaryResponseDTO toSummaryResponse(Product product) {
    String coverUrl =
        product.getColors().stream()
            .flatMap(c -> c.getImages().stream())
            .filter(ProductImage::getIsCover)
            .map(ProductImage::getUrl)
            .findFirst()
            .orElse(null);

    List<ColorSummaryResponseDTO> colors =
        product.getColors().stream().map(this::toColorSummaryResponse).toList();

    return new ProductSummaryResponseDTO(
        product.getId(), product.getName(), product.getPrice(), coverUrl, colors);
  }

  default ColorSummaryResponseDTO toColorSummaryResponse(ProductColor color) {
    List<String> availableSizes =
        color.getVariants().stream()
            .filter(v -> v.getStock() > 0)
            .map(v -> v.getProductSize().name())
            .toList();

    return new ColorSummaryResponseDTO(
        color.getId(), color.getName(), color.getHexCode(), availableSizes);
  }
}
