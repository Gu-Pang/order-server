package org.gupang.order.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record ProductResponseDto(
        @JsonProperty("id")
        UUID productId,
        UUID companyId,
        Integer stock,
        Long price
) {
}
