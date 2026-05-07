package com.tsm.atelier.domain.order.dto.v1.response;

import java.util.UUID;

public record CheckoutResponseDTO(UUID orderId, String redirectUrl) {}
