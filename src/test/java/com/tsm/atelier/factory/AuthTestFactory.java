package com.tsm.atelier.factory;

import com.tsm.atelier.domain.auth.EmailVerificationToken;
import com.tsm.atelier.domain.auth.Role;
import com.tsm.atelier.domain.auth.User;
import com.tsm.atelier.domain.auth.UserStatus;
import com.tsm.atelier.domain.auth.dto.v1.request.LoginRequestDTO;
import com.tsm.atelier.domain.auth.dto.v1.request.RegisterRequestDTO;
import java.time.Instant;
import java.util.UUID;

public class AuthTestFactory {

  public static RegisterRequestBuilder aRegisterRequest() {
    return new RegisterRequestBuilder();
  }

  public static UserBuilder aUser() {
    return new UserBuilder();
  }

  public static LoginRequestBuilder aLoginRequest() {
    return new LoginRequestBuilder();
  }

  public static RefreshTokenBuilder aRefreshToken() {
    return new RefreshTokenBuilder();
  }

  public static VerificationTokenBuilder aVerificationToken() {
    return new VerificationTokenBuilder();
  }

  public static class LoginRequestBuilder {
    private String email = "ronaldinho@email.com";
    private String password = "password123";

    public LoginRequestBuilder withEmail(String email) {
      this.email = email;
      return this;
    }

    public LoginRequestBuilder withPassword(String password) {
      this.password = password;
      return this;
    }

    public LoginRequestDTO build() {
      return new LoginRequestDTO(email, password);
    }
  }

  public static class RegisterRequestBuilder {
    private String email = "ronaldinho@email.com";
    private String password = "password123";
    private String firstName = "Ronaldo";
    private String lastName = "Moreira";

    public RegisterRequestBuilder withEmail(String email) {
      this.email = email;
      return this;
    }

    public RegisterRequestBuilder withPassword(String password) {
      this.password = password;
      return this;
    }

    public RegisterRequestBuilder withFirstName(String firstName) {
      this.firstName = firstName;
      return this;
    }

    public RegisterRequestBuilder withLastName(String lastName) {
      this.lastName = lastName;
      return this;
    }

    public RegisterRequestDTO build() {
      return new RegisterRequestDTO(email, password, firstName, lastName);
    }
  }

  public static class UserBuilder {
    private UUID id = UUID.randomUUID();
    private String email = "ronaldinho@email.com";
    private String password = "hashed_password";
    private final Role role = Role.CLIENT;
    private UserStatus status = UserStatus.INACTIVE;
    private boolean emailVerified = false;

    public UserBuilder withId(UUID id) {
      this.id = id;
      return this;
    }

    public UserBuilder withEmail(String email) {
      this.email = email;
      return this;
    }

    public UserBuilder withStatus(UserStatus status) {
      this.status = status;
      return this;
    }

    public UserBuilder withEmailVerified(boolean emailVerified) {
      this.emailVerified = emailVerified;
      return this;
    }

    public User build() {
      User user = new User();
      user.setId(id);
      user.setEmail(email);
      user.setPassword(password);
      user.setRole(role);
      user.setStatus(status);
      user.setEmailVerified(emailVerified);
      return user;
    }
  }

  public static class RefreshTokenBuilder {
    private String tokenHash = "valid_refresh_token_hash";
    private UUID familyId = UUID.randomUUID();
    private User user = new UserBuilder().build();
    private Instant expiresAt = Instant.now().plusSeconds(604800);
    private Boolean revoked = false;

    public RefreshTokenBuilder withUser(User user) {
      this.user = user;
      return this;
    }

    public RefreshTokenBuilder withTokenHash(String tokenHash) {
      this.tokenHash = tokenHash;
      return this;
    }

    public RefreshTokenBuilder withFamilyId(UUID familyId) {
      this.familyId = familyId;
      return this;
    }

    public RefreshTokenBuilder revoked() {
      this.revoked = true;
      return this;
    }

    public RefreshTokenBuilder expired() {
      this.expiresAt = Instant.now().minusSeconds(1);
      return this;
    }

    public com.tsm.atelier.domain.auth.RefreshToken build() {
      com.tsm.atelier.domain.auth.RefreshToken refreshToken =
          new com.tsm.atelier.domain.auth.RefreshToken();
      refreshToken.setTokenHash(tokenHash);
      refreshToken.setFamilyId(familyId);
      refreshToken.setUser(user);
      refreshToken.setExpiresAt(expiresAt);
      refreshToken.setRevoked(revoked);
      return refreshToken;
    }
  }

  public static class VerificationTokenBuilder {
    private String token = "valid_token";
    private User user = new UserBuilder().build();
    private Instant expiresAt = Instant.now().plusSeconds(14400);
    private Boolean used = false;

    public VerificationTokenBuilder withUser(User user) {
      this.user = user;
      return this;
    }

    public VerificationTokenBuilder used() {
      this.used = true;
      return this;
    }

    public VerificationTokenBuilder expired() {
      this.expiresAt = Instant.now().minusSeconds(1);
      return this;
    }

    public EmailVerificationToken build() {
      EmailVerificationToken verificationToken = new EmailVerificationToken();
      verificationToken.setToken(token);
      verificationToken.setUser(user);
      verificationToken.setExpiresAt(expiresAt);
      verificationToken.setUsed(used);
      return verificationToken;
    }
  }
}
