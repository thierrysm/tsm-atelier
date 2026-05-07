package com.tsm.atelier.domain.order;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShippingAddress {

  @Column(name = "ship_recipient_name", length = 120, nullable = false)
  private String recipientName;

  @Column(name = "ship_cep", length = 9, nullable = false)
  private String cep;

  @Column(name = "ship_street", nullable = false)
  private String street;

  @Column(name = "ship_number", length = 20, nullable = false)
  private String number;

  @Column(name = "ship_complement", length = 100)
  private String complement;

  @Column(name = "ship_neighborhood", length = 100, nullable = false)
  private String neighborhood;

  @Column(name = "ship_city", length = 100, nullable = false)
  private String city;

  @Column(name = "ship_state", length = 2, nullable = false)
  private String state;
}
