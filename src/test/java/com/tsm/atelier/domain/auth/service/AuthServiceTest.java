package com.tsm.atelier.domain.auth.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tsm.atelier.config.ApplicationProperties;
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
import com.tsm.atelier.factory.AuthTestFactory;
import com.tsm.atelier.shared.security.JwtService;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
@DisplayName("AuthService")
class AuthServiceTest {

  @Mock private UserRepository userRepository;
  @Mock private RefreshTokenRepository refreshTokenRepository;
  @Mock private ClientProfileRepository clientProfileRepository;
  @Mock private EmailVerificationTokenRepository emailVerificationTokenRepository;
  @Mock private PasswordEncoder passwordEncoder;
  @Mock private ApplicationEventPublisher eventPublisher;
  @Mock private ApplicationProperties applicationProperties;
  @Mock private AuthenticationManager authenticationManager;
  @Mock private JwtService jwtService;
  @Mock private SecurityProperties securityProperties;

  @InjectMocks private AuthService authService;

  private Authentication authResultFor(User user) {
    return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
  }

  @Nested
  @DisplayName("Registro de usuário")
  class RegisterUser {

    @Test
    @DisplayName("Deve registrar usuário e publicar evento com sucesso")
    void shouldRegisterUserAndPublishEventSuccessfully() {
      // Arrange
      RegisterRequestDTO request = AuthTestFactory.aRegisterRequest().build();
      User mockUser = AuthTestFactory.aUser().withEmail(request.email()).build();

      when(userRepository.existsByEmail(request.email())).thenReturn(false);
      when(passwordEncoder.encode(request.password())).thenReturn("hashed_password");
      when(userRepository.save(any(User.class))).thenReturn(mockUser);
      when(applicationProperties.emailVerificationExpiration()).thenReturn(14400L);

      // Act
      authService.register(request);

      // Assert
      verify(userRepository).save(any(User.class));
      verify(clientProfileRepository).save(any(ClientProfile.class));
      verify(emailVerificationTokenRepository).save(any(EmailVerificationToken.class));
      verify(eventPublisher).publishEvent(any(UserRegisteredEvent.class));
    }

    @Test
    @DisplayName("Deve criar usuário com status INACTIVE")
    void shouldCreateUserWithInactiveStatus() {
      // Arrange
      RegisterRequestDTO request = AuthTestFactory.aRegisterRequest().build();
      User mockUser = AuthTestFactory.aUser().withStatus(UserStatus.INACTIVE).build();

      when(userRepository.existsByEmail(any())).thenReturn(false);
      when(passwordEncoder.encode(any())).thenReturn("hashed_password");
      when(userRepository.save(any(User.class))).thenReturn(mockUser);
      when(applicationProperties.emailVerificationExpiration()).thenReturn(14400L);

      // Act
      authService.register(request);

      // Assert
      verify(userRepository).save(argThat(user -> user.getStatus().equals(UserStatus.INACTIVE)));
    }

    @Test
    @DisplayName("Deve criar usuário com role CLIENT")
    void shouldCreateUserWithClientRole() {
      // Arrange
      RegisterRequestDTO request = AuthTestFactory.aRegisterRequest().build();
      User mockUser = AuthTestFactory.aUser().build();

      when(userRepository.existsByEmail(any())).thenReturn(false);
      when(passwordEncoder.encode(any())).thenReturn("hashed_password");
      when(userRepository.save(any(User.class))).thenReturn(mockUser);
      when(applicationProperties.emailVerificationExpiration()).thenReturn(14400L);

      // Act
      authService.register(request);

      // Assert
      verify(userRepository).save(argThat(user -> user.getRole().equals(Role.CLIENT)));
    }

    @Test
    @DisplayName("Deve encriptar a senha antes de salvar")
    void shouldEncryptPasswordBeforeSaving() {
      // Arrange
      RegisterRequestDTO request =
          AuthTestFactory.aRegisterRequest().withPassword("senha123").build();
      User mockUser = AuthTestFactory.aUser().build();

      when(userRepository.existsByEmail(any())).thenReturn(false);
      when(passwordEncoder.encode("senha123")).thenReturn("hashed_senha123");
      when(userRepository.save(any(User.class))).thenReturn(mockUser);
      when(applicationProperties.emailVerificationExpiration()).thenReturn(14400L);

      // Act
      authService.register(request);

      // Assert
      verify(userRepository).save(argThat(user -> user.getPassword().equals("hashed_senha123")));
    }

    @Test
    @DisplayName("Deve lançar exceção quando email já existe")
    void shouldThrowExceptionWhenEmailAlreadyExists() {
      // Arrange
      RegisterRequestDTO request = AuthTestFactory.aRegisterRequest().build();
      when(userRepository.existsByEmail(request.email())).thenReturn(true);

      // Act & Assert
      assertThatThrownBy(() -> authService.register(request))
          .isInstanceOf(EntityAlreadyExistsException.class)
          .hasMessageContaining("Usuário");

      verify(userRepository, never()).save(any());
      verify(eventPublisher, never()).publishEvent(any());
    }

    @Test
    @DisplayName("Não deve publicar evento quando falha ao salvar usuário")
    void shouldNotPublishEventWhenUserSaveFails() {
      // Arrange
      RegisterRequestDTO request = AuthTestFactory.aRegisterRequest().build();

      when(userRepository.existsByEmail(any())).thenReturn(false);
      when(passwordEncoder.encode(any())).thenReturn("hashed_password");
      when(userRepository.save(any())).thenThrow(new RuntimeException("DB error"));

      // Act & Assert
      assertThatThrownBy(() -> authService.register(request)).isInstanceOf(RuntimeException.class);

      verify(eventPublisher, never()).publishEvent(any());
    }

    @Test
    @DisplayName("Deve criar perfil com nome e sobrenome do request")
    void shouldCreateProfileWithNameFromRequest() {
      // Arrange
      RegisterRequestDTO request =
          AuthTestFactory.aRegisterRequest().withFirstName("João").withLastName("Silva").build();
      User mockUser = AuthTestFactory.aUser().build();

      when(userRepository.existsByEmail(any())).thenReturn(false);
      when(passwordEncoder.encode(any())).thenReturn("hashed_password");
      when(userRepository.save(any())).thenReturn(mockUser);
      when(applicationProperties.emailVerificationExpiration()).thenReturn(14400L);

      // Act
      authService.register(request);

      // Assert
      verify(clientProfileRepository)
          .save(
              argThat(
                  profile ->
                      profile.getFirstName().equals("João")
                          && profile.getLastName().equals("Silva")));
    }
  }

  @Nested
  @DisplayName("Reenviar verificação")
  class ResendVerification {

    @Test
    @DisplayName(
        "Deve invalidar tokens antigos, criar novo e publicar evento para usuário INACTIVE")
    void shouldInvalidateOldTokensAndPublishEventForInactiveUser() {
      // Arrange
      User user =
          AuthTestFactory.aUser().withStatus(UserStatus.INACTIVE).withEmailVerified(false).build();
      when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
      when(applicationProperties.emailVerificationExpiration()).thenReturn(14400L);

      // Act
      authService.resendVerification(user.getEmail());

      // Assert
      verify(emailVerificationTokenRepository).invalidateActiveByUserId(user.getId());
      verify(emailVerificationTokenRepository).save(any(EmailVerificationToken.class));
      verify(eventPublisher)
          .publishEvent(argThat((UserRegisteredEvent ev) -> ev.email().equals(user.getEmail())));
    }

    @Test
    @DisplayName("Não deve fazer nada quando usuário não é encontrado (resposta opaca)")
    void shouldDoNothingWhenUserNotFound() {
      // Arrange
      when(userRepository.findByEmail("nao-existe@email.com")).thenReturn(Optional.empty());

      // Act
      authService.resendVerification("nao-existe@email.com");

      // Assert
      verify(emailVerificationTokenRepository, never()).invalidateActiveByUserId(any());
      verify(emailVerificationTokenRepository, never()).save(any());
      verify(eventPublisher, never()).publishEvent(any());
    }

    @Test
    @DisplayName("Não deve reenviar quando usuário já está ACTIVE")
    void shouldNotResendWhenUserIsActive() {
      // Arrange
      User user = AuthTestFactory.aUser().withStatus(UserStatus.ACTIVE).build();
      when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

      // Act
      authService.resendVerification(user.getEmail());

      // Assert
      verify(emailVerificationTokenRepository, never()).invalidateActiveByUserId(any());
      verify(eventPublisher, never()).publishEvent(any());
    }

    @Test
    @DisplayName("Não deve reenviar quando emailVerified=true (mesmo se status difere)")
    void shouldNotResendWhenEmailAlreadyVerified() {
      // Arrange
      User user =
          AuthTestFactory.aUser().withStatus(UserStatus.INACTIVE).withEmailVerified(true).build();
      when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

      // Act
      authService.resendVerification(user.getEmail());

      // Assert
      verify(emailVerificationTokenRepository, never()).invalidateActiveByUserId(any());
      verify(eventPublisher, never()).publishEvent(any());
    }
  }

  @Nested
  @DisplayName("Login de usuário")
  class LoginUser {

    @Test
    @DisplayName("Deve fazer login com sucesso e retornar tokens")
    void shouldLoginSuccessfullyAndReturnTokens() {
      // Arrange
      LoginRequestDTO request = AuthTestFactory.aLoginRequest().build();
      User user = AuthTestFactory.aUser().withStatus(UserStatus.ACTIVE).build();

      when(authenticationManager.authenticate(any())).thenReturn(authResultFor(user));
      when(jwtService.generateAccessToken(user)).thenReturn("access_token");
      when(jwtService.generateRefreshToken()).thenReturn("refresh_token");
      when(jwtService.hashRefreshToken("refresh_token")).thenReturn("refresh_token_hash");
      when(securityProperties.refreshTokenExpiration()).thenReturn(604800L);

      // Act
      AuthResponseDTO response = authService.login(request);

      // Assert
      assertThat(response.accessToken()).isEqualTo("access_token");
      assertThat(response.refreshToken()).isEqualTo("refresh_token");
      verify(refreshTokenRepository).save(any(RefreshToken.class));
    }

    @Test
    @DisplayName("Deve salvar refresh token hash no banco com nova família")
    void shouldSaveRefreshTokenOnLogin() {
      // Arrange
      LoginRequestDTO request = AuthTestFactory.aLoginRequest().build();
      User user = AuthTestFactory.aUser().withStatus(UserStatus.ACTIVE).build();

      when(authenticationManager.authenticate(any())).thenReturn(authResultFor(user));
      when(jwtService.generateAccessToken(any())).thenReturn("access_token");
      when(jwtService.generateRefreshToken()).thenReturn("refresh_token");
      when(jwtService.hashRefreshToken("refresh_token")).thenReturn("refresh_token_hash");
      when(securityProperties.refreshTokenExpiration()).thenReturn(604800L);

      // Act
      authService.login(request);

      // Assert
      verify(refreshTokenRepository)
          .save(
              argThat(
                  token ->
                      token.getTokenHash().equals("refresh_token_hash")
                          && !token.getRevoked()
                          && token.getFamilyId() != null
                          && token.getUser().equals(user)));
    }
  }

  @Nested
  @DisplayName("Refresh token")
  class RefreshTokenFlow {

    @Test
    @DisplayName("Deve renovar tokens com sucesso e manter a mesma família")
    void shouldRefreshTokensSuccessfully() {
      // Arrange
      User user = AuthTestFactory.aUser().withStatus(UserStatus.ACTIVE).build();
      UUID familyId = UUID.randomUUID();
      RefreshToken existingToken =
          AuthTestFactory.aRefreshToken().withUser(user).withFamilyId(familyId).build();

      when(jwtService.hashRefreshToken("valid_refresh_token")).thenReturn("hash_of_valid");
      when(refreshTokenRepository.findByTokenHash("hash_of_valid"))
          .thenReturn(Optional.of(existingToken));
      when(jwtService.generateAccessToken(user)).thenReturn("new_access_token");
      when(jwtService.generateRefreshToken()).thenReturn("new_refresh_token");
      when(jwtService.hashRefreshToken("new_refresh_token")).thenReturn("hash_of_new");
      when(securityProperties.refreshTokenExpiration()).thenReturn(604800L);

      // Act
      AuthResponseDTO response =
          authService.refresh(new RefreshTokenRequestDTO("valid_refresh_token"));

      // Assert
      assertThat(response.accessToken()).isEqualTo("new_access_token");
      assertThat(response.refreshToken()).isEqualTo("new_refresh_token");
      verify(refreshTokenRepository)
          .save(
              argThat(
                  token ->
                      familyId.equals(token.getFamilyId())
                          && "hash_of_new".equals(token.getTokenHash())
                          && Boolean.FALSE.equals(token.getRevoked())));
    }

    @Test
    @DisplayName("Deve revogar o token antigo ao renovar")
    void shouldRevokeOldTokenOnRefresh() {
      // Arrange
      User user = AuthTestFactory.aUser().build();
      RefreshToken existingToken = AuthTestFactory.aRefreshToken().withUser(user).build();

      when(jwtService.hashRefreshToken("valid_refresh_token")).thenReturn("hash");
      when(refreshTokenRepository.findByTokenHash("hash")).thenReturn(Optional.of(existingToken));
      when(jwtService.generateAccessToken(any())).thenReturn("new_access_token");
      when(jwtService.generateRefreshToken()).thenReturn("new_refresh_token");
      when(jwtService.hashRefreshToken("new_refresh_token")).thenReturn("hash_of_new");
      when(securityProperties.refreshTokenExpiration()).thenReturn(604800L);

      // Act
      authService.refresh(new RefreshTokenRequestDTO("valid_refresh_token"));

      // Assert
      assertThat(existingToken.getRevoked()).isTrue();
      verify(refreshTokenRepository).save(existingToken);
    }

    @Test
    @DisplayName("Deve lançar exceção quando refresh token não existe")
    void shouldThrowExceptionWhenRefreshTokenNotFound() {
      // Arrange
      when(jwtService.hashRefreshToken(any())).thenReturn("hash");
      when(refreshTokenRepository.findByTokenHash(any())).thenReturn(Optional.empty());

      // Act & Assert
      assertThatThrownBy(() -> authService.refresh(new RefreshTokenRequestDTO("invalid_token")))
          .isInstanceOf(BusinessException.class)
          .hasMessageContaining("inválido");
    }

    @Test
    @DisplayName("Reuso de token revogado deve revogar a família inteira")
    void shouldRevokeFamilyOnReuse() {
      // Arrange
      UUID familyId = UUID.randomUUID();
      RefreshToken revokedToken =
          AuthTestFactory.aRefreshToken().revoked().withFamilyId(familyId).build();

      when(jwtService.hashRefreshToken("revoked_token")).thenReturn("hash");
      when(refreshTokenRepository.findByTokenHash("hash")).thenReturn(Optional.of(revokedToken));

      // Act & Assert
      assertThatThrownBy(() -> authService.refresh(new RefreshTokenRequestDTO("revoked_token")))
          .isInstanceOf(BusinessException.class)
          .hasMessageContaining("inválido");

      verify(refreshTokenRepository).revokeFamily(eq(familyId));
    }

    @Test
    @DisplayName("Deve lançar exceção quando refresh token está expirado")
    void shouldThrowExceptionWhenRefreshTokenIsExpired() {
      // Arrange
      RefreshToken expiredToken = AuthTestFactory.aRefreshToken().expired().build();

      when(jwtService.hashRefreshToken(any())).thenReturn("hash");
      when(refreshTokenRepository.findByTokenHash(any())).thenReturn(Optional.of(expiredToken));

      // Act & Assert
      assertThatThrownBy(() -> authService.refresh(new RefreshTokenRequestDTO("expired_token")))
          .isInstanceOf(BusinessException.class)
          .hasMessageContaining("inválido");
    }
  }

  @Nested
  @DisplayName("Verificação de email")
  class VerifyEmail {

    @Test
    @DisplayName("Deve verificar email e ativar conta com sucesso")
    void shouldVerifyEmailAndActivateAccountSuccessfully() {
      // Arrange
      User user =
          AuthTestFactory.aUser().withStatus(UserStatus.INACTIVE).withEmailVerified(false).build();
      EmailVerificationToken verificationToken =
          AuthTestFactory.aVerificationToken().withUser(user).build();

      when(emailVerificationTokenRepository.findByToken("valid_token"))
          .thenReturn(Optional.of(verificationToken));

      // Act
      authService.verifyEmail("valid_token");

      // Assert
      assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
      assertThat(user.getEmailVerified()).isTrue();
      verify(userRepository).save(user);
    }

    @Test
    @DisplayName("Deve marcar token como usado após verificação")
    void shouldMarkTokenAsUsedAfterVerification() {
      // Arrange
      User user = AuthTestFactory.aUser().withStatus(UserStatus.INACTIVE).build();
      EmailVerificationToken verificationToken =
          AuthTestFactory.aVerificationToken().withUser(user).build();

      when(emailVerificationTokenRepository.findByToken(any()))
          .thenReturn(Optional.of(verificationToken));

      // Act
      authService.verifyEmail("valid_token");

      // Assert
      assertThat(verificationToken.getUsed()).isTrue();
      verify(emailVerificationTokenRepository).save(verificationToken);
    }

    @Test
    @DisplayName("Deve lançar exceção quando token não existe")
    void shouldThrowExceptionWhenTokenNotFound() {
      // Arrange
      when(emailVerificationTokenRepository.findByToken(any())).thenReturn(Optional.empty());

      // Act & Assert
      assertThatThrownBy(() -> authService.verifyEmail("invalid_token"))
          .isInstanceOf(BusinessException.class)
          .hasMessageContaining("inválido");

      verify(userRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando token já foi utilizado")
    void shouldThrowExceptionWhenTokenAlreadyUsed() {
      // Arrange
      EmailVerificationToken usedToken = AuthTestFactory.aVerificationToken().used().build();

      when(emailVerificationTokenRepository.findByToken(any())).thenReturn(Optional.of(usedToken));

      // Act & Assert
      assertThatThrownBy(() -> authService.verifyEmail("used_token"))
          .isInstanceOf(BusinessException.class)
          .hasMessageContaining("expirado ou já utilizado");

      verify(userRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando token está expirado")
    void shouldThrowExceptionWhenTokenIsExpired() {
      // Arrange
      EmailVerificationToken expiredToken = AuthTestFactory.aVerificationToken().expired().build();

      when(emailVerificationTokenRepository.findByToken(any()))
          .thenReturn(Optional.of(expiredToken));

      // Act & Assert
      assertThatThrownBy(() -> authService.verifyEmail("expired_token"))
          .isInstanceOf(BusinessException.class)
          .hasMessageContaining("expirado ou já utilizado");

      verify(userRepository, never()).save(any());
    }
  }

  @Nested
  @DisplayName("Logout de usuário")
  class LogoutUser {

    @Test
    @DisplayName("Deve fazer logout e revogar refresh token com sucesso")
    void shouldLogoutAndRevokeRefreshTokenSuccessfully() {
      // Arrange
      RefreshToken refreshToken = AuthTestFactory.aRefreshToken().build();

      when(jwtService.hashRefreshToken("valid_refresh_token")).thenReturn("hash");
      when(refreshTokenRepository.findByTokenHash("hash")).thenReturn(Optional.of(refreshToken));

      // Act
      authService.logout(new RefreshTokenRequestDTO("valid_refresh_token"));

      // Assert
      assertThat(refreshToken.getRevoked()).isTrue();
      verify(refreshTokenRepository).save(refreshToken);
    }

    @Test
    @DisplayName("Deve lançar exceção quando refresh token não existe no logout")
    void shouldThrowExceptionWhenRefreshTokenNotFoundOnLogout() {
      // Arrange
      when(jwtService.hashRefreshToken(any())).thenReturn("hash");
      when(refreshTokenRepository.findByTokenHash(any())).thenReturn(Optional.empty());

      // Act & Assert
      assertThatThrownBy(() -> authService.logout(new RefreshTokenRequestDTO("invalid_token")))
          .isInstanceOf(BusinessException.class)
          .hasMessageContaining("inválido");

      verify(refreshTokenRepository, never()).save(any());
    }
  }
}
