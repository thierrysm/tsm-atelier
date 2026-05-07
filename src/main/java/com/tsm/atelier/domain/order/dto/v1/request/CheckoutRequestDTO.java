package com.tsm.atelier.domain.order.dto.v1.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record CheckoutRequestDTO(
    @NotEmpty @Valid List<CheckoutItemRequestDTO> items,
    @NotNull @Valid ShippingAddressRequestDTO shippingAddress) {}
