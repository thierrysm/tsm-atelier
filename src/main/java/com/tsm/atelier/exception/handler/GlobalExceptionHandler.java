package com.tsm.atelier.exception.handler;

import com.tsm.atelier.exception.ProductAlreadyExistsException;
import com.tsm.atelier.exception.ProductNotFoundException;
import com.tsm.atelier.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiError> handleResourceNotFoundException(
      ResourceNotFoundException ex, HttpServletRequest request) {
    ApiError error =
        new ApiError(
            404,
            "Not Found",
            ex.getClass().getName(),
            ex.getMessage(),
            request.getRequestURI(),
            LocalDateTime.now(),
            null);
    return ResponseEntity.status(404).body(error);
  }

  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<ApiError> handleProductNotFoundException(
      ProductNotFoundException ex, HttpServletRequest request) {
    ApiError error =
        new ApiError(
            404,
            "Not Found",
            ex.getClass().getName(),
            ex.getMessage(),
            request.getRequestURI(),
            LocalDateTime.now(),
            null);
    return ResponseEntity.status(404).body(error);
  }

  @ExceptionHandler(ProductAlreadyExistsException.class)
  public ResponseEntity<ApiError> handleProductAlreadyExistsException(
      ProductAlreadyExistsException ex, HttpServletRequest request) {
    ApiError error =
        new ApiError(
            409,
            "Conflict",
            ex.getClass().getName(),
            ex.getMessage(),
            request.getRequestURI(),
            LocalDateTime.now(),
            null);
    return ResponseEntity.status(409).body(error);
  }

  @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> handleValidationException(
      org.springframework.web.bind.MethodArgumentNotValidException ex, HttpServletRequest request) {
    java.util.List<ApiError.FieldError> fieldErrors =
        ex.getBindingResult().getFieldErrors().stream()
            .map(error -> new ApiError.FieldError(error.getField(), error.getDefaultMessage()))
            .toList();

    ApiError error =
        new ApiError(
            400,
            "Bad Request",
            ex.getClass().getName(),
            "Validation error",
            request.getRequestURI(),
            LocalDateTime.now(),
            fieldErrors);
    return ResponseEntity.status(400).body(error);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleGenericException(Exception ex, HttpServletRequest request) {
    ApiError error =
        new ApiError(
            500,
            "Internal Server Error",
            ex.getClass().getName(),
            ex.getMessage(),
            request.getRequestURI(),
            LocalDateTime.now(),
            null);
    return ResponseEntity.status(500).body(error);
  }
}
