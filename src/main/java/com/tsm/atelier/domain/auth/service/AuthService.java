package com.tsm.atelier.domain.auth.service;

import com.tsm.atelier.config.SecurityProperties;
import com.tsm.atelier.domain.auth.EmailVerificationToken;
import com.tsm.atelier.domain.auth.RefreshToken;
import com.tsm.atelier.domain.auth.Role;
import com.tsm.atelier.domain.auth.User;
import com.tsm.atelier.domain.auth.UserStatus;
import com.tsm.atelier.domain.auth.dto.v1.request.LoginRequestDTO;
import com.tsm.atelier.domain.auth.dto.v1.request.RefreshTokenRequestDTO;
import com.tsm.atelier.domain.auth.dto.v1.request.RegisterRequestDTO;
import com.tsm.atelier.domain.auth.dto.v1.response.AuthResponseDTO;
import com.tsm.atelier.domain.auth.repository.EmailVerificationTokenRepository;
import com.tsm.atelier.domain.auth.repository.RefreshTokenRepository;
import com.tsm.atelier.domain.auth.repository.UserRepository;
import com.tsm.atelier.domain.client.ClientProfile;
import com.tsm.atelier.domain.client.ClientProfileRepository;
import com.tsm.atelier.domain.common.UserRegisteredEvent;
import com.tsm.atelier.exception.BusinessException;
import com.tsm.atelier.exception.EntityAlreadyExistsException;
import com.tsm.atelier.shared.security.JwtService;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

  private static final Logger log = LoggerFactory.getLogger(AuthService.class);
  private static final String GENERIC_REFRESH_ERROR = "Refresh token inválido";

  private final UserRepository userRepository;
  private final RefreshTokenRepository refreshTokenRepository;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final SecurityProperties properties;
  private final ClientProfileRepository clientProfileRepository;
  private final ApplicationEventPublisher applicationEventPublisher;
  private final EmailVerificationTokenRepository emailVerificationTokenRepository;
  private final UserVerificationService userVerificationService;

  @Transactional
  public void register(RegisterRequestDTO request) {
    if (userRepository.existsByEmail(request.email())) {
      throw new EntityAlreadyExistsException("Usuário", "email", request.email());
    }

    User savedUser = createUser(request);
    createClientProfile(request, savedUser);
    String token = userVerificationService.createVerificationToken(savedUser);

    applicationEventPublisher.publishEvent(new UserRegisteredEvent(request.email(), token));
  }

  @Transactional
  public AuthResponseDTO login(LoginRequestDTO request) {
    try {
      Authentication authentication =
          authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(request.email(), request.password()));

      User user = (User) authentication.getPrincipal();

      String accessToken = jwtService.generateAccessToken(user);
      String refreshToken = saveRefreshToken(user, UUID.randomUUID());

      return new AuthResponseDTO(accessToken, refreshToken);
    } catch (DisabledException ex) {
      User user =
          userRepository
              .findByEmail(request.email())
              .orElseThrow(() -> new BadCredentialsException("Email ou senha inválidos"));

      if (!passwordEncoder.matches(request.password(), user.getPassword())) {
        log.warn("Tentativa de login em conta inativa com senha incorreta: {}", request.email());
        throw new BadCredentialsException("Email ou senha inválidos");
      }

      log.info("Login falhou - conta inativa. Disparando reenvio para: {}", request.email());
      userVerificationService.resendVerification(request.email());
      throw ex;
    }
  }

  @Transactional
  public AuthResponseDTO refresh(RefreshTokenRequestDTO request) {
    String hash = jwtService.hashRefreshToken(request.refreshToken());
    RefreshToken stored =
        refreshTokenRepository
            .findByTokenHash(hash)
            .orElseThrow(() -> new BusinessException(GENERIC_REFRESH_ERROR));

    if (Boolean.TRUE.equals(stored.getRevoked())) {
      refreshTokenRepository.revokeFamily(stored.getFamilyId());
      log.warn(
          "REFRESH_TOKEN_REUSE_DETECTED userId={} family={}",
          stored.getUser().getId(),
          stored.getFamilyId());
      throw new BusinessException(GENERIC_REFRESH_ERROR);
    }

    if (stored.isExpired()) {
      throw new BusinessException(GENERIC_REFRESH_ERROR);
    }

    User user = stored.getUser();

    stored.setRevoked(true);
    refreshTokenRepository.save(stored);

    String newAccessToken = jwtService.generateAccessToken(user);
    String newRefreshToken = saveRefreshToken(user, stored.getFamilyId());

    return new AuthResponseDTO(newAccessToken, newRefreshToken);
  }

  @Transactional
  public void verifyEmail(String token) {
    EmailVerificationToken verificationToken =
        emailVerificationTokenRepository
            .findByToken(token)
            .orElseThrow(() -> new BusinessException("Token de verificação inválido"));

    if (!verificationToken.isValid()) {
      throw new BusinessException("Token de verificação expirado ou já utilizado");
    }

    User user = verificationToken.getUser();
    user.setStatus(UserStatus.ACTIVE);
    user.setEmailVerified(true);
    userRepository.save(user);

    verificationToken.setUsed(true);
    emailVerificationTokenRepository.save(verificationToken);
  }

  @Transactional
  public void logout(RefreshTokenRequestDTO request) {
    String hash = jwtService.hashRefreshToken(request.refreshToken());
    RefreshToken refreshToken =
        refreshTokenRepository
            .findByTokenHash(hash)
            .orElseThrow(() -> new BusinessException(GENERIC_REFRESH_ERROR));

    refreshToken.setRevoked(true);
    refreshTokenRepository.save(refreshToken);
  }

  private String saveRefreshToken(User user, UUID familyId) {
    String token = jwtService.generateRefreshToken();
    Instant expiresAt = Instant.now().plusSeconds(properties.refreshTokenExpiration());

    RefreshToken refreshToken = new RefreshToken();
    refreshToken.setTokenHash(jwtService.hashRefreshToken(token));
    refreshToken.setFamilyId(familyId);
    refreshToken.setUser(user);
    refreshToken.setExpiresAt(expiresAt);
    refreshToken.setRevoked(false);

    refreshTokenRepository.save(refreshToken);
    return token;
  }

  private User createUser(RegisterRequestDTO request) {
    User user = new User();
    user.setEmail(request.email());
    user.setPassword(passwordEncoder.encode(request.password()));
    user.setRole(Role.CLIENT);
    user.setStatus(UserStatus.INACTIVE);
    return userRepository.save(user);
  }

  private void createClientProfile(RegisterRequestDTO request, User user) {
    ClientProfile profile = new ClientProfile();
    profile.setUser(user);
    profile.setFirstName(request.firstName());
    profile.setLastName(request.lastName());
    clientProfileRepository.save(profile);
  }
}
