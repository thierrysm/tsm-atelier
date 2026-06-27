package com.tsm.atelier.domain.auth.controller.v1;

import com.tsm.atelier.config.SecurityProperties;
import com.tsm.atelier.domain.auth.dto.v1.request.LoginRequestDTO;
import com.tsm.atelier.domain.auth.dto.v1.request.RefreshTokenRequestDTO;
import com.tsm.atelier.domain.auth.dto.v1.request.RegisterRequestDTO;
import com.tsm.atelier.domain.auth.dto.v1.response.AccessTokenResponseDTO;
import com.tsm.atelier.domain.auth.dto.v1.response.AuthResponseDTO;
import com.tsm.atelier.domain.auth.service.AuthService;
import com.tsm.atelier.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;
  private final SecurityProperties properties;

  private static final String REFRESH_TOKEN_COOKIE = "refresh_token";

  @PostMapping("/register")
  public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequestDTO request) {
    authService.register(request);
    return ResponseEntity.status(201).build();
  }

  @PostMapping("/login")
  public ResponseEntity<AccessTokenResponseDTO> login(
      @Valid @RequestBody LoginRequestDTO request, HttpServletResponse response) {
    AuthResponseDTO auth = authService.login(request);
    addRefreshTokenCookie(response, auth.refreshToken());
    return ResponseEntity.ok(new AccessTokenResponseDTO(auth.accessToken()));
  }

  @PostMapping("/refresh")
  public ResponseEntity<AccessTokenResponseDTO> refresh(
      @CookieValue(name = REFRESH_TOKEN_COOKIE, required = false) String refreshToken,
      HttpServletRequest request,
      HttpServletResponse response) {
    requireAllowedOrigin(request);

    if (refreshToken == null) {
      throw new BusinessException("Refresh token não encontrado");
    }

    AuthResponseDTO auth = authService.refresh(new RefreshTokenRequestDTO(refreshToken));
    addRefreshTokenCookie(response, auth.refreshToken());
    return ResponseEntity.ok(new AccessTokenResponseDTO(auth.accessToken()));
  }

  @PostMapping("/logout")
  public ResponseEntity<Void> logout(
      @CookieValue(name = REFRESH_TOKEN_COOKIE, required = false) String refreshToken,
      HttpServletRequest request,
      HttpServletResponse response) {
    requireAllowedOrigin(request);

    if (refreshToken != null) {
      authService.logout(new RefreshTokenRequestDTO(refreshToken));
    }

    clearRefreshTokenCookie(response);
    return ResponseEntity.noContent().build();
  }

  private void addRefreshTokenCookie(HttpServletResponse response, String refreshToken) {
    ResponseCookie cookie =
        ResponseCookie.from(REFRESH_TOKEN_COOKIE, refreshToken)
            .httpOnly(true)
            .secure(true)
            .sameSite("Lax")
            .path("/api/v1/auth")
            .maxAge(properties.refreshTokenExpiration())
            .build();

    response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
  }

  private void clearRefreshTokenCookie(HttpServletResponse response) {
    ResponseCookie cookie =
        ResponseCookie.from(REFRESH_TOKEN_COOKIE, "")
            .httpOnly(true)
            .secure(true)
            .sameSite("Lax")
            .path("/api/v1/auth")
            .maxAge(Duration.ZERO)
            .build();

    response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
  }

  private void requireAllowedOrigin(HttpServletRequest request) {
    List<String> allowed = properties.allowedOrigins();
    if (allowed == null || allowed.isEmpty()) {
      return;
    }

    String origin = request.getHeader("Origin");
    String referer = request.getHeader("Referer");

    if (matchesAllowed(origin, allowed) || matchesAllowed(referer, allowed)) {
      return;
    }

    throw new AccessDeniedException("Origem não permitida");
  }

  private boolean matchesAllowed(String header, List<String> allowed) {
    if (header == null || header.isBlank()) {
      return false;
    }
    String normalized = normalizeOrigin(header);
    if (normalized == null) {
      return false;
    }
    return allowed.contains(normalized);
  }

  private String normalizeOrigin(String header) {
    try {
      URI uri = new URI(header);
      if (uri.getScheme() == null || uri.getHost() == null) {
        return null;
      }
      String origin = uri.getScheme() + "://" + uri.getHost();
      if (uri.getPort() != -1) {
        origin += ":" + uri.getPort();
      }
      return origin;
    } catch (URISyntaxException ex) {
      return null;
    }
  }
}
