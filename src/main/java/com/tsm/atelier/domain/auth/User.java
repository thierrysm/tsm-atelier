package com.tsm.atelier.domain.auth;

import com.tsm.atelier.shared.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Getter
@Setter
@SQLRestriction("status != 'BANNED'")
public class User extends BaseEntity implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 10)
  private Role role;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 10)
  private UserStatus status = UserStatus.INACTIVE;

  @Column(nullable = false)
  private Boolean emailVerified = false;

  @Override
  @NonNull
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
  }

  @Override
  @NonNull
  public String getUsername() {
    return email;
  }

  @Override
  @NonNull
  public String getPassword() {
    return password;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !status.equals(UserStatus.BANNED);
  }

  @Override
  public boolean isEnabled() {
    return status.equals(UserStatus.ACTIVE);
  }
}
