package com.tsm.atelier.domain.product.repository;

import com.tsm.atelier.domain.product.Product;
import com.tsm.atelier.domain.product.ProductCategory;
import com.tsm.atelier.domain.product.ProductColor;
import com.tsm.atelier.domain.product.ProductSize;
import com.tsm.atelier.domain.product.ProductStatus;
import com.tsm.atelier.domain.product.ProductVariant;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import java.math.BigDecimal;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

  private ProductSpecification() {}

  public static Specification<Product> withStatusActive() {
    return (root, query, cb) -> cb.equal(root.get("status"), ProductStatus.ACTIVE);
  }

  public static Specification<Product> hasNameContaining(String nameQuery) {
    return (root, query, cb) -> {
      if (nameQuery == null || nameQuery.isBlank()) {
        return null;
      }
      String pattern = "%" + nameQuery.toLowerCase() + "%";
      return cb.like(cb.lower(root.get("name")), pattern);
    };
  }

  public static Specification<Product> hasCategory(ProductCategory category) {
    return (root, query, cb) -> {
      if (category == null) {
        return null;
      }
      return cb.equal(root.get("category"), category);
    };
  }

  public static Specification<Product> hasCollection(Long collectionId) {
    return (root, query, cb) -> {
      if (collectionId == null) {
        return null;
      }
      return cb.equal(root.get("collection").get("id"), collectionId);
    };
  }

  public static Specification<Product> hasPriceBetween(BigDecimal min, BigDecimal max) {
    return (root, query, cb) -> {
      if (min != null && max != null) {
        return cb.between(root.get("price"), min, max);
      } else if (min != null) {
        return cb.greaterThanOrEqualTo(root.get("price"), min);
      } else if (max != null) {
        return cb.lessThanOrEqualTo(root.get("price"), max);
      }
      return null;
    };
  }

  public static Specification<Product> hasSizeAndStock(ProductSize size) {
    return (root, query, cb) -> {
      if (size == null) {
        return null;
      }
      // Prevent duplicate results when using pagination with joins
      query.distinct(true);

      Join<Product, ProductColor> colorsJoin = root.join("colors", JoinType.INNER);
      Join<ProductColor, ProductVariant> variantsJoin = colorsJoin.join("variants", JoinType.INNER);

      return cb.and(
          cb.equal(variantsJoin.get("size"), size), cb.greaterThan(variantsJoin.get("stock"), 0));
    };
  }
}
