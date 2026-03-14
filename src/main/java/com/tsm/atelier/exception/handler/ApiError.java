package com.tsm.atelier.exception.handler;

import java.time.LocalDateTime;
import java.util.List;

public record ApiError(
    int status,
    String error,
    String exception,
    String message,
    String path,
    LocalDateTime timestamp,
    List<FieldError> fields) {
  public record FieldError(String field, String message) {}
}
