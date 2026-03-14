package com.tsm.atelier.exception;

public class EntityAlreadyExistsException extends RuntimeException {

  public EntityAlreadyExistsException(String entityName, String field, String value) {
    super(entityName + " com " + field + " '" + value + "' já cadastrado(a)");
  }

  public EntityAlreadyExistsException(String message) {
    super(message);
  }
}
