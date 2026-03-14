package com.tsm.atelier.factory;

import com.tsm.atelier.domain.collection.Collection;
import com.tsm.atelier.domain.product.Product;
import com.tsm.atelier.domain.product.ProductCare;
import com.tsm.atelier.domain.product.ProductCategory;
import com.tsm.atelier.domain.product.ProductColor;
import com.tsm.atelier.domain.product.ProductComposition;
import com.tsm.atelier.domain.product.ProductStatus;
import com.tsm.atelier.domain.product.dto.v1.request.ProductCareRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductCompositionRequestDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductPatchDTO;
import com.tsm.atelier.domain.product.dto.v1.request.ProductRequestDTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductTestFactory {

  public static ProductBuilder aProduct() {
    return new ProductBuilder();
  }

  public static ProductRequestBuilder aProductRequest() {
    return new ProductRequestBuilder();
  }

  public static class ProductRequestBuilder {
    private String name = "Vestido Linho Premium";
    private String description = "Vestido confeccionado em linho premium.";
    private BigDecimal price = BigDecimal.valueOf(299.90);
    private ProductCategory category = ProductCategory.VESTIDOS;
    private Long collectionId = 1L;
    private List<ProductCompositionRequestDTO> compositions =
        List.of(ProductCompositionTestFactory.aProductCompositionRequest());
    private List<ProductCareRequestDTO> careInstructions =
        List.of(ProductCareTestFactory.aCareRequest());

    public ProductRequestBuilder withoutCollection() {
      this.collectionId = null;
      return this;
    }

    public ProductRequestBuilder withCollectionId(Long collectionId) {
      this.collectionId = collectionId;
      return this;
    }

    public ProductRequestBuilder withName(String name) {
      this.name = name;
      return this;
    }

    public ProductRequestDTO build() {
      return new ProductRequestDTO(
          name, description, price, category, collectionId, compositions, careInstructions);
    }
  }

  public static ProductPatchDTO aProductPatch() {
    return new ProductPatchDTO(
        Optional.of("Vestido Linho Atualizado"),
        Optional.empty(),
        Optional.empty(),
        Optional.empty(),
        Optional.empty(),
        Optional.empty());
  }

  public static class ProductBuilder {
    private Long id = 1L;
    private String name = "Vestido Linho Premium";
    private String slug = "vestido-linho-premium";
    private String description = "Vestido confeccionado em linho premium.";
    private BigDecimal price = BigDecimal.valueOf(299.90);
    private ProductCategory category = ProductCategory.VESTIDOS;
    private ProductStatus status = ProductStatus.ACTIVE;
    private Collection collection = CollectionTestFactory.aCollection().build();
    private List<ProductColor> colors = new ArrayList<>();
    private List<ProductCare> careInstructions = new ArrayList<>();
    private List<ProductComposition> compositions = new ArrayList<>();

    public ProductBuilder withId(Long id) {
      this.id = id;
      return this;
    }

    public ProductBuilder withName(String name) {
      this.name = name;
      return this;
    }

    public ProductBuilder withStatus(ProductStatus status) {
      this.status = status;
      return this;
    }

    public ProductBuilder withCollection(Collection collection) {
      this.collection = collection;
      return this;
    }

    public ProductBuilder withColors(List<ProductColor> colors) {
      this.colors = colors;
      return this;
    }

    public ProductBuilder withoutCollection() {
      this.collection = null;
      return this;
    }

    public Product build() {
      Product product = new Product();
      product.setId(id);
      product.setName(name);
      product.setSlug(slug);
      product.setDescription(description);
      product.setPrice(price);
      product.setCategory(category);
      product.setStatus(status);
      product.setCollection(collection);
      product.setColors(colors);
      product.setCareInstructions(careInstructions);
      product.setCompositions(compositions);
      return product;
    }
  }
}
