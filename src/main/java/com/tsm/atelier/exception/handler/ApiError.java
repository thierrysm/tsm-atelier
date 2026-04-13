package com.tsm.atelier.exception.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiError(
    int status,
    String error,
    String exception,
    String message,
    String path,
    Instant timestamp,
    List<FieldError> fields) {
  public record FieldError(String field, String message) {}
}
