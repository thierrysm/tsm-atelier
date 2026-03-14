package com.tsm.atelier.exception;

import java.util.List;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
  private final List<String> errors;

  public BusinessException(String message) {
    super(message);
    this.errors = List.of(message);
  }

  public BusinessException(List<String> errors) {
    super("Erro de validação de negócio");
    this.errors = errors;
  }
}
