package com.tsm.atelier.shared.security;

import com.tsm.atelier.exception.RateLimitExceededException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Component
@RequiredArgsConstructor
public class RateLimitFilter extends OncePerRequestFilter {

  private static final Duration WINDOW = Duration.ofMinutes(15);

  // Limites por IP por janela de 15 min para endpoints sensíveis a brute force / spam.
  private static final Map<String, Integer> LIMITS_BY_PATH =
      Map.of(
          "POST /api/v1/auth/login", 30,
          "POST /api/v1/auth/refresh", 60,
          "POST /api/v1/auth/forgot-password", 10,
          "POST /api/v1/auth/register", 5,
          "POST /api/v1/auth/resend-verification", 5);

  private final RateLimiter rateLimiter;
  private final HandlerExceptionResolver handlerExceptionResolver;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {

    String key = request.getMethod() + " " + request.getRequestURI();
    Integer limit = LIMITS_BY_PATH.get(key);

    if (limit == null) {
      filterChain.doFilter(request, response);
      return;
    }

    String clientKey = key + "|" + clientIp(request);
    if (!rateLimiter.tryAcquire(clientKey, limit, WINDOW)) {
      handlerExceptionResolver.resolveException(
          request,
          response,
          null,
          new RateLimitExceededException(
              "Limite de tentativas excedido. Tente novamente em alguns minutos."));
      return;
    }

    filterChain.doFilter(request, response);
  }

  private String clientIp(HttpServletRequest request) {
    String forwarded = request.getHeader("X-Forwarded-For");
    if (forwarded != null && !forwarded.isBlank()) {
      List<String> ips = List.of(forwarded.split(","));
      return ips.get(0).trim();
    }
    return request.getRemoteAddr();
  }
}
