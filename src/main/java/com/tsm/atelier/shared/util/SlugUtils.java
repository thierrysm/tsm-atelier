package com.tsm.atelier.shared.util;

import java.text.Normalizer;

public class SlugUtils {

  public static String generate(String name) {
    return Normalizer.normalize(name, Normalizer.Form.NFD) // separa acentos
        .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "") // remove acentos
        .toLowerCase()
        .trim()
        .replaceAll("[^a-z0-9\\s-]", "") // remove caracteres especiais
        .replaceAll("[\\s]+", "-"); // troca espaços por hífen
  }
}
