package com.tsm.atelier.domain.auth.controller.v1;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsm.atelier.config.SecurityProperties;
import com.tsm.atelier.domain.auth.dto.v1.request.LoginRequestDTO;
import com.tsm.atelier.domain.auth.dto.v1.request.RefreshTokenRequestDTO;
import com.tsm.atelier.domain.auth.dto.v1.request.RegisterRequestDTO;
import com.tsm.atelier.domain.auth.dto.v1.response.AuthResponseDTO;
import com.tsm.atelier.domain.auth.service.AuthService;
import com.tsm.atelier.exception.BusinessException;
import com.tsm.atelier.exception.EntityAlreadyExistsException;
import com.tsm.atelier.exception.handler.GlobalExceptionHandler;
import com.tsm.atelier.factory.AuthTestFactory;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@DisplayName("AuthController")
class AuthControllerTest {

  private MockMvc mockMvc;

  @Mock private AuthService authService;

  @Mock private SecurityProperties properties;

  @InjectMocks private AuthController authController;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  void setUp() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(authController)
            .setControllerAdvice(new GlobalExceptionHandler())
            .build();
  }

  @Nested
  @DisplayName("Registro")
  class Register {

    @Test
    @DisplayName("Deve registrar usuário com sucesso")
    void shouldRegisterUserSuccessfully() throws Exception {
      // Arrange
      RegisterRequestDTO request = AuthTestFactory.aRegisterRequest().build();
      doNothing().when(authService).register(any(RegisterRequestDTO.class));

      // Act & Assert
      mockMvc
          .perform(
              post("/api/v1/auth/register")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isCreated());

      verify(authService).register(any(RegisterRequestDTO.class));
    }

    @Test
    @DisplayName("Deve retornar 409 Conflict se email já existir")
    void shouldReturnConflictWhenEmailExists() throws Exception {
      // Arrange
      RegisterRequestDTO request = AuthTestFactory.aRegisterRequest().build();
      doThrow(new EntityAlreadyExistsException("Usuário", "email", request.email()))
          .when(authService)
          .register(any(RegisterRequestDTO.class));

      // Act & Assert
      mockMvc
          .perform(
              post("/api/v1/auth/register")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isConflict())
          .andExpect(jsonPath("$.status").value(409))
          .andExpect(jsonPath("$.error").value("Conflito"))
          .andExpect(
              jsonPath("$.message")
                  .value("Usuário com email '" + request.email() + "' já cadastrado(a)"));
    }

    @Test
    @DisplayName("Deve retornar 400 Bad Request com payload inválido")
    void shouldReturnBadRequestWithInvalidPayload() throws Exception {
      // Arrange
      RegisterRequestDTO invalidRequest = new RegisterRequestDTO(null, null, null, null);

      // Act & Assert
      mockMvc
          .perform(
              post("/api/v1/auth/register")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(invalidRequest)))
          .andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.status").value(400))
          .andExpect(jsonPath("$.error").value("Dados Inválidos"));
    }
  }

  @Nested
  @DisplayName("Login")
  class Login {

    @Test
    @DisplayName("Deve fazer login e retornar tokens no body e cookie")
    void shouldLoginSuccessfully() throws Exception {
      // Arrange
      LoginRequestDTO request = AuthTestFactory.aLoginRequest().build();
      AuthResponseDTO authResponse = new AuthResponseDTO("access_token", "refresh_token");

      when(authService.login(any(LoginRequestDTO.class))).thenReturn(authResponse);
      when(properties.refreshTokenExpiration()).thenReturn(604800L);

      // Act & Assert
      mockMvc
          .perform(
              post("/api/v1/auth/login")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.accessToken").value("access_token"))
          .andExpect(cookie().exists("refresh_token"))
          .andExpect(cookie().value("refresh_token", "refresh_token"))
          .andExpect(cookie().httpOnly("refresh_token", true))
          .andExpect(cookie().secure("refresh_token", true));

      verify(authService).login(any(LoginRequestDTO.class));
    }

    @Test
    @DisplayName("Deve retornar 401 Unauthorized para credenciais inválidas")
    void shouldReturnUnauthorizedForBadCredentials() throws Exception {
      // Arrange
      LoginRequestDTO request = AuthTestFactory.aLoginRequest().build();
      doThrow(new BadCredentialsException("Bad credentials"))
          .when(authService)
          .login(any(LoginRequestDTO.class));

      // Act & Assert
      mockMvc
          .perform(
              post("/api/v1/auth/login")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isUnauthorized())
          .andExpect(jsonPath("$.status").value(401))
          .andExpect(jsonPath("$.error").value("Não Autorizado"))
          .andExpect(jsonPath("$.message").value("Email ou senha inválidos"));
    }

    @Test
    @DisplayName("Conta desativada também retorna mensagem genérica (anti-enumeration)")
    void shouldReturnGenericMessageWhenAccountIsDisabled() throws Exception {
      // Arrange
      LoginRequestDTO request = AuthTestFactory.aLoginRequest().build();
      doThrow(new org.springframework.security.authentication.DisabledException("disabled"))
          .when(authService)
          .login(any(LoginRequestDTO.class));

      // Act & Assert
      mockMvc
          .perform(
              post("/api/v1/auth/login")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isUnauthorized())
          .andExpect(jsonPath("$.message").value("Email ou senha inválidos"));
    }
  }

  @Nested
  @DisplayName("Refresh Token")
  class Refresh {

    @Test
    @DisplayName("Deve renovar token quando cookie refresh_token for fornecido")
    void shouldRefreshTokenSuccessfully() throws Exception {
      // Arrange
      Cookie cookie = new Cookie("refresh_token", "old_refresh_token");
      AuthResponseDTO authResponse = new AuthResponseDTO("new_access_token", "new_refresh_token");

      when(authService.refresh(any(RefreshTokenRequestDTO.class))).thenReturn(authResponse);
      when(properties.refreshTokenExpiration()).thenReturn(604800L);

      // Act & Assert
      mockMvc
          .perform(post("/api/v1/auth/refresh").cookie(cookie))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.accessToken").value("new_access_token"))
          .andExpect(cookie().exists("refresh_token"))
          .andExpect(cookie().value("refresh_token", "new_refresh_token"));

      verify(authService).refresh(any(RefreshTokenRequestDTO.class));
    }

    @Test
    @DisplayName("Deve retornar BadRequest se refresh_token nao for enviado")
    void shouldReturnBadRequestWhenCookieIsMissing() throws Exception {
      // Act & Assert
      mockMvc
          .perform(post("/api/v1/auth/refresh"))
          .andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.status").value(400))
          .andExpect(jsonPath("$.error").value("Erro de Negócio"))
          .andExpect(jsonPath("$.message").value("Refresh token não encontrado"));
    }

    @Test
    @DisplayName("Deve retornar 400 Bad Request se refresh token for inválido")
    void shouldReturnBadRequestWhenRefreshTokenIsInvalid() throws Exception {
      // Arrange
      Cookie cookie = new Cookie("refresh_token", "invalid_refresh_token");
      doThrow(new BusinessException("Refresh token inválido"))
          .when(authService)
          .refresh(any(RefreshTokenRequestDTO.class));

      // Act & Assert
      mockMvc
          .perform(post("/api/v1/auth/refresh").cookie(cookie))
          .andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.status").value(400))
          .andExpect(jsonPath("$.message").value("Refresh token inválido"));
    }
  }

  @Nested
  @DisplayName("Verificação de Email")
  class VerifyEmail {

    @Test
    @DisplayName("Deve verificar email com sucesso")
    void shouldVerifyEmailSuccessfully() throws Exception {
      // Arrange
      String token = "valid_token";
      doNothing().when(authService).verifyEmail(token);

      // Act & Assert
      mockMvc
          .perform(get("/api/v1/auth/verify").param("token", token))
          .andExpect(status().isNoContent());

      verify(authService).verifyEmail(token);
    }

    @Test
    @DisplayName("Deve retornar 400 Bad Request para token inválido")
    void shouldReturnBadRequestWhenTokenIsInvalid() throws Exception {
      // Arrange
      String token = "invalid_token";
      doThrow(new BusinessException("Token de verificação inválido"))
          .when(authService)
          .verifyEmail(token);

      // Act & Assert
      mockMvc
          .perform(get("/api/v1/auth/verify").param("token", token))
          .andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.status").value(400))
          .andExpect(jsonPath("$.message").value("Token de verificação inválido"));
    }
  }

  @Nested
  @DisplayName("Logout")
  class Logout {

    @Test
    @DisplayName("Deve realizar logout e limpar o cookie refresh_token")
    void shouldLogoutSuccessfully() throws Exception {
      // Arrange
      Cookie cookie = new Cookie("refresh_token", "valid_refresh_token");
      doNothing().when(authService).logout(any(RefreshTokenRequestDTO.class));

      // Act & Assert
      mockMvc
          .perform(post("/api/v1/auth/logout").cookie(cookie))
          .andExpect(status().isNoContent())
          .andExpect(cookie().exists("refresh_token"))
          .andExpect(cookie().value("refresh_token", ""))
          .andExpect(cookie().maxAge("refresh_token", 0));

      verify(authService).logout(any(RefreshTokenRequestDTO.class));
    }

    @Test
    @DisplayName("Deve realizar logout limpando o cookie mesmo sem enviar o refresh_token")
    void shouldLogoutAndClearCookieEvenWithoutRefreshToken() throws Exception {
      // Act & Assert
      mockMvc
          .perform(post("/api/v1/auth/logout"))
          .andExpect(status().isNoContent())
          .andExpect(cookie().exists("refresh_token"))
          .andExpect(cookie().value("refresh_token", ""))
          .andExpect(cookie().maxAge("refresh_token", 0));
    }
  }
}
