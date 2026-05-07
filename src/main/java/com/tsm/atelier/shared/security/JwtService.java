package com.tsm.atelier.shared.security;

import com.tsm.atelier.config.SecurityProperties;
import com.tsm.atelier.domain.auth.Role;
import com.tsm.atelier.domain.auth.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HexFormat;
import java.util.UUID;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

  private static final SecureRandom RANDOM = new SecureRandom();

  private final SecurityProperties properties;

  public String generateAccessToken(User user) {
    Instant now = Instant.now();
    Instant expiration = now.plusSeconds(properties.accessTokenExpiration());

    return Jwts.builder()
        .subject(user.getId().toString())
        .claim("email", user.getEmail())
        .claim("role", user.getRole().name())
        .issuer(properties.issuer())
        .audience()
        .add(properties.audience())
        .and()
        .id(UUID.randomUUID().toString())
        .issuedAt(Date.from(now))
        .expiration(Date.from(expiration))
        .signWith(getSigningKey())
        .compact();
  }

  public String generateRefreshToken() {
    byte[] bytes = new byte[32];
    RANDOM.nextBytes(bytes);
    return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
  }

  public String hashRefreshToken(String token) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hash = digest.digest(token.getBytes(StandardCharsets.UTF_8));
      return HexFormat.of().formatHex(hash);
    } catch (NoSuchAlgorithmException ex) {
      throw new IllegalStateException("SHA-256 indisponível", ex);
    }
  }

  public Claims parseClaims(String token) {
    return Jwts.parser()
        .verifyWith(getSigningKey())
        .requireIssuer(properties.issuer())
        .requireAudience(properties.audience())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  public UUID extractUserId(Claims claims) {
    return UUID.fromString(claims.getSubject());
  }

  public String extractEmail(Claims claims) {
    return claims.get("email", String.class);
  }

  public Role extractRole(Claims claims) {
    return Role.valueOf(claims.get("role", String.class));
  }

  public String extractJti(Claims claims) {
    return claims.getId();
  }

  private SecretKey getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(properties.secret());
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
