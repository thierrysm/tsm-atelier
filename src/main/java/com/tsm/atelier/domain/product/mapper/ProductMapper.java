package com.tsm.atelier.domain.product.mapper;

import com.tsm.atelier.domain.collection.Collection;
import com.tsm.atelier.domain.product.CompositionMaterial;
import com.tsm.atelier.domain.product.Product;
import com.tsm.atelier.domain.product.ProductCare;
import com.tsm.atelier.domain.product.ProductColor;
import com.tsm.atelier.domain.product.ProductComposition;
import com.tsm.atelier.domain.product.ProductImage;
import com.tsm.atelier.domain.product.ProductVariant;
import com.tsm.atelier.domain.product.dto.v1.request.CompositionMaterialRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductCareRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductColorRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductCompositionRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductImageRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductVariantRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ColorSummaryResponseDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductCareResponseDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductColorDetailsResponseDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductColorResponseDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductCompositionResponseDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductDetailsResponseDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductImageResponseDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductSummaryResponseDTO;
import com.tsm.atelier.domain.product.dto.v1.response.ProductVariantResponseDTO;
import java.util.List;
import java.util.Optional;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "slug", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "collection", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
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

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "product", ignore = true)
  ProductComposition toEntity(ProductCompositionRequestDTO request);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "composition", ignore = true)
  CompositionMaterial toEntity(CompositionMaterialRequestDTO request);

  @AfterMapping
  default void setCompositionBackReference(@MappingTarget ProductComposition composition) {
    if (composition.getMaterials() != null) {
      composition.getMaterials().forEach(material -> material.setComposition(composition));
    }
  }

  @AfterMapping
  default void setBackReferences(@MappingTarget Product product) {
    if (product.getCareInstructions() != null) {
      product.getCareInstructions().forEach(care -> care.setProduct(product));
    }

    if (product.getCompositions() != null) {
      product
          .getCompositions()
          .forEach(
              composition -> {
                composition.setProduct(product); // ← seta o product_id
                if (composition.getMaterials() != null) {
                  composition
                      .getMaterials()
                      .forEach(material -> material.setComposition(composition));
                }
              });
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

  default ProductDetailsResponseDTO toDetailsResponse(Product product) {
    if (product == null) {
      return null;
    }

    String collectionName =
        Optional.ofNullable(product.getCollection()).map(Collection::getName).orElse(null);

    return new ProductDetailsResponseDTO(
        product.getId(),
        product.getName(),
        product.getSlug(),
        product.getDescription(),
        product.getPrice(),
        product.getStatus(),
        product.getCompositions().stream().map(this::toCompositionResponse).toList(),
        product.getCategory(),
        collectionName,
        product.getCareInstructions().stream().map(this::toCareResponse).toList(),
        product.getColors().stream().map(this::toDetailsColorResponse).toList());
  }

  default ProductColorDetailsResponseDTO toDetailsColorResponse(ProductColor color) {
    if (color == null) {
      return null;
    }

    return new ProductColorDetailsResponseDTO(
        color.getId(),
        color.getName(),
        color.getHexCode(),
        color.getImages().stream().map(this::toImageResponse).toList(),
        color.getVariants().stream().map(this::toVariantResponse).toList());
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
            .map(v -> v.getSize().name())
            .toList();

    return new ColorSummaryResponseDTO(
        color.getId(), color.getName(), color.getHexCode(), availableSizes);
  }

  ProductColorDetailsResponseDTO toColorDetailsResponse(ProductColor color);

  ProductColorResponseDTO toColorResponse(ProductColor color);

  ProductImageResponseDTO toImageResponse(ProductImage image);

  ProductVariantResponseDTO toVariantResponse(ProductVariant variant);

  ProductCareResponseDTO toCareResponse(ProductCare care);

  ProductCompositionResponseDTO toCompositionResponse(ProductComposition composition);
}
