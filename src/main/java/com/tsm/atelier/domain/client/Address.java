package com.tsm.atelier.domain.client;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 9)
  private String cep;

  @Column(nullable = false)
  private String street;

  @Column(nullable = false, length = 10)
  private String number;

  @Column(length = 100)
  private String complement;

  @Column(nullable = false, length = 100)
  private String neighborhood;

  @Column(nullable = false, length = 100)
  private String city;

  @Column(nullable = false, length = 2)
  private String state;

  @Column(nullable = false)
  private Boolean isDefault = false;

  @ManyToOne
  @JoinColumn(name = "client_profile_id", nullable = false)
  private ClientProfile clientProfile;
}
