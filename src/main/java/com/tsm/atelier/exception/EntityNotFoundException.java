package com.tsm.atelier.exception;

public class EntityNotFoundException extends RuntimeException {
  public EntityNotFoundException(String entityName, String field, Object value) {
    super(entityName + " com " + field + " '" + value + "' não encontrado(a)");
  }

  public EntityNotFoundException(String message) {
    super(message);
  }
}
