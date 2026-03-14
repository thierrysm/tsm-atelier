package com.tsm.atelier.shared.image;

import lombok.Getter;

@Getter
public enum ImageFolder {
  PRODUCTS("products"),
  COLLECTIONS("collections"),
  ASSETS("assets");

  private final String path;

  ImageFolder(String path) {
    this.path = path;
  }
}
