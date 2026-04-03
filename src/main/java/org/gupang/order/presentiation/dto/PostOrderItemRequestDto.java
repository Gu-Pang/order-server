package org.gupang.order.presentiation.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;


public record PostOrderItemRequestDto(
        @NotNull(message = "상품 ID는 필수 입력값입니다.")
        UUID productId,

        @NotNull(message = "상품 가격은 필수 입력값입니다.")
        @Positive(message = "상품 가격은 0보다 커야 합니다.")
        Long totalPrice,

        @NotNull(message = "주문 수량은 필수 입력값입니다.")
        @Min(value = 1, message = "주문 수량은 최소 1개 이상이어야 합니다.")
        Integer quantity
){}
