package com.tsm.atelier.exception.handler;

import com.tsm.atelier.exception.BusinessException;
import com.tsm.atelier.exception.EntityAlreadyExistsException;
import com.tsm.atelier.exception.EntityNotFoundException;
import com.tsm.atelier.exception.RateLimitExceededException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

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

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> handleValidationException(
      MethodArgumentNotValidException ex, HttpServletRequest request) {
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

  @ExceptionHandler(io.jsonwebtoken.JwtException.class)
  public ResponseEntity<ApiError> handleJwtException(
      io.jsonwebtoken.JwtException ex, HttpServletRequest request) {
    ApiError error =
        new ApiError(
            401,
            "Não Autorizado",
            ex.getClass().getName(),
            "Token JWT inválido ou expirado.",
            request.getRequestURI(),
            Instant.now(),
            null);
    return ResponseEntity.status(401).body(error);
  }

  @ExceptionHandler({
    BadCredentialsException.class,
    LockedException.class,
    UsernameNotFoundException.class
  })
  public ResponseEntity<ApiError> handleAuthFailure(
      AuthenticationException ex, HttpServletRequest request) {
    log.warn(
        "Falha de autenticação em {}: {}", request.getRequestURI(), ex.getClass().getSimpleName());
    ApiError error =
        new ApiError(
            401,
            "Não Autorizado",
            ex.getClass().getName(),
            "Email ou senha inválidos",
            request.getRequestURI(),
            Instant.now(),
            null);
    return ResponseEntity.status(401).body(error);
  }

  @ExceptionHandler(DisabledException.class)
  public ResponseEntity<ApiError> handleDisabledAccount(
      DisabledException ex, HttpServletRequest request) {
    log.info("Tentativa de login em conta não ativada: {}", request.getRequestURI());
    ApiError error =
        new ApiError(
            403,
            "Conta Não Ativada",
            ex.getClass().getName(),
            "Sua conta ainda não foi confirmada. Enviamos um novo e-mail de ativação para você.",
            request.getRequestURI(),
            Instant.now(),
            null);
    return ResponseEntity.status(403).body(error);
  }

  @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
  public ResponseEntity<ApiError> handleOptimisticLock(
      ObjectOptimisticLockingFailureException ex, HttpServletRequest request) {
    log.warn(
        "Conflito de versão (optimistic lock) em {}: {}", request.getRequestURI(), ex.getMessage());
    ApiError error =
        new ApiError(
            HttpStatus.CONFLICT.value(),
            "Conflito de Concorrência",
            ex.getClass().getName(),
            "O recurso foi modificado por outra requisição. Tente novamente.",
            request.getRequestURI(),
            Instant.now(),
            null);
    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ApiError> handleDataIntegrityViolation(
      DataIntegrityViolationException ex, HttpServletRequest request) {
    log.warn(
        "Violação de integridade de dados em {}: {}", request.getRequestURI(), ex.getMessage());
    ApiError error =
        new ApiError(
            HttpStatus.CONFLICT.value(),
            "Conflito",
            ex.getClass().getName(),
            "Não foi possível concluir a operação. Pode haver um valor duplicado ou uma referência inválida.",
            request.getRequestURI(),
            Instant.now(),
            null);
    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ApiError> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex, HttpServletRequest request) {
    ApiError error =
        new ApiError(
            HttpStatus.BAD_REQUEST.value(),
            "Requisição Inválida",
            ex.getClass().getName(),
            "Corpo da requisição malformado ou JSON inválido.",
            request.getRequestURI(),
            Instant.now(),
            null);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ApiError> handleTypeMismatch(
      MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
    ApiError error =
        new ApiError(
            HttpStatus.BAD_REQUEST.value(),
            "Parâmetro Inválido",
            ex.getClass().getName(),
            "O parâmetro '" + ex.getName() + "' possui um valor inválido.",
            request.getRequestURI(),
            Instant.now(),
            null);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ApiError> handleAccessDenied(
      AccessDeniedException ex, HttpServletRequest request) {
    ApiError error =
        new ApiError(
            HttpStatus.FORBIDDEN.value(),
            "Acesso Negado",
            ex.getClass().getName(),
            "Você não tem permissão para acessar este recurso.",
            request.getRequestURI(),
            Instant.now(),
            null);
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleGeneric(Exception ex, HttpServletRequest request) {
    log.error("Erro inesperado em {}", request.getRequestURI(), ex);
    ApiError error =
        new ApiError(
            500,
            "Erro Interno",
            ex.getClass().getName(),
            "Ocorreu um erro inesperado. Tente novamente mais tarde.",
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

  @ExceptionHandler(RateLimitExceededException.class)
  public ResponseEntity<ApiError> handleRateLimit(
      RateLimitExceededException ex, HttpServletRequest request) {
    log.warn("Rate limit excedido em {}", request.getRequestURI());
    ApiError error =
        new ApiError(
            HttpStatus.TOO_MANY_REQUESTS.value(),
            "Muitas Requisições",
            ex.getClass().getName(),
            ex.getMessage(),
            request.getRequestURI(),
            Instant.now(),
            null);
    return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(error);
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
            fields);
    return ResponseEntity.status(400).body(error);
  }
}
