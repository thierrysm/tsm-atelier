package com.tsm.atelier.domain.product.dto.v1.response;

import java.util.List;
import java.util.UUID;

public record ColorSummaryResponseDTO(
    UUID id, String name, String hexCode, List<String> availableSizes) {}
