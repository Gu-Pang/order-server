package org.gupang.order.presentiation.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record PostOrderRequestDto (
        @NotNull(message = "수령자 ID는 필수 입력값입니다.")
        UUID receiverId,

        String message,

        @NotNull
        String address,

        String detailAddress,

        @NotEmpty(message = "주문할 상품 목록은 비어있을 수 없습니다.")
        @Valid
        List <OrderItemRequestDto> orderItems
){}
