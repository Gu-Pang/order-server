package org.gupang.order.presentiation.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateOrderRequestDto(
        @NotNull
        String address,
        String detailAddress,
        String message,
        UUID productId,
        @Min(1)
        Integer quantity
) {
}
