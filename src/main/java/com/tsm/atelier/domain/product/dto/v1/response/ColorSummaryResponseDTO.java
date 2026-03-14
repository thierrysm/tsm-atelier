package com.tsm.atelier.domain.product.dto.v1.response;

import java.util.List;

public record ColorSummaryResponseDTO(
    Long id, String name, String hexCode, List<String> availableSizes) {}
