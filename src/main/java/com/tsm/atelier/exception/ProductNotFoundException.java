package com.tsm.atelier.exception;

public class ProductNotFoundException extends RuntimeException {
  public ProductNotFoundException(String type, String value) {
    super("Product with " + type + ": '" + value + "' not found");
  }
}
