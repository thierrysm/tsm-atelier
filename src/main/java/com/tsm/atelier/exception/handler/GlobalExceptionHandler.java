package com.tsm.atelier.exception.handler;

import com.tsm.atelier.exception.BusinessException;
import com.tsm.atelier.exception.EntityAlreadyExistsException;
import com.tsm.atelier.exception.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResponseEntity<ApiError> handleMaxUploadSizeExceededException(
      MaxUploadSizeExceededException ex, HttpServletRequest request) {
    ApiError error =
        new ApiError(
            413,
            "Arquivo muito grande",
            ex.getClass().getName(),
            "Tamanho máximo de upload excedido. Por favor, envie um arquivo menor que 10MB.",
            request.getRequestURI(),
            Instant.now(),
            null);
    return ResponseEntity.status(413).body(error);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ApiError> handleResourceNotFoundException(
      EntityNotFoundException ex, HttpServletRequest request) {
    ApiError error =
        new ApiError(
            404,
            "Não Encontrado",
            ex.getClass().getName(),
            ex.getMessage(),
            request.getRequestURI(),
            Instant.now(),
            null);
    return ResponseEntity.status(404).body(error);
  }

  @ExceptionHandler(EntityAlreadyExistsException.class)
  public ResponseEntity<ApiError> handleEntityAlreadyExistsException(
      EntityAlreadyExistsException ex, HttpServletRequest request) {
    ApiError error =
        new ApiError(
            409,
            "Conflito",
            ex.getClass().getName(),
            ex.getMessage(),
            request.getRequestURI(),
            Instant.now(),
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
            "Dados Inválidos",
            ex.getClass().getName(),
            "Um ou mais campos são inválidos",
            request.getRequestURI(),
            Instant.now(),
            fieldErrors);
    return ResponseEntity.status(400).body(error);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleGeneric(Exception ex, HttpServletRequest request) {
    ApiError error =
        new ApiError(
            500,
            "Erro Interno",
            "Ocorreu um erro inesperado. Tente novamente mais tarde.",
            ex.getMessage(),
            request.getRequestURI(),
            Instant.now(),
            null);
    return ResponseEntity.status(500).body(error);
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<ApiError> handleNoHandlerFound(
      NoHandlerFoundException ex, HttpServletRequest request) {
    ApiError error =
        new ApiError(
            404,
            "Não Encontrado",
            "O endpoint " + ex.getRequestURL() + " não existe.",
            ex.getMessage(),
            request.getRequestURI(),
            Instant.now(),
            null);
    return ResponseEntity.status(404).body(error);
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ApiError> handleBusiness(BusinessException ex, HttpServletRequest request) {
    List<ApiError.FieldError> fields =
        ex.getErrors().stream().map(error -> new ApiError.FieldError(null, error)).toList();

    ApiError error =
        new ApiError(
            400,
            "Erro de Negócio",
            ex.getClass().getName(),
            ex.getMessage(),
            request.getRequestURI(),
            Instant.now(),
            null);
    return ResponseEntity.status(400).body(error);
  }
}
