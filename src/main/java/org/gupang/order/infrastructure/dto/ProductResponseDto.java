package org.gupang.order.infrastructure.dto;

import java.util.UUID;

public record ProductResponseDto(
        UUID productId,
        UUID companyId,
        Integer stock,
        Long price
) {
}
