package com.tsm.atelier.domain.client;

import com.tsm.atelier.domain.auth.User;
import com.tsm.atelier.shared.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "client_profiles")
@Getter
@Setter
@NoArgsConstructor
public class ClientProfile extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 60)
  private String firstName;

  @Column(length = 60)
  private String lastName;

  @Column(unique = true, length = 14)
  private String cpf;

  @Column(length = 11)
  private String phone;

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false, unique = true)
  private User user;

  @OneToMany(mappedBy = "clientProfile", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Address> addresses = new ArrayList<>();
}
