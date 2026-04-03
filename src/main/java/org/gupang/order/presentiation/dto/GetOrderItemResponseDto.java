package org.gupang.order.presentiation.dto;

import org.gupang.order.domain.OrderItem;

import java.util.UUID;

public record GetOrderItemResponseDto(
        UUID productId,
        Long totalPrice,
        Integer quantity
) {
    public static GetOrderItemResponseDto from(OrderItem item) {
        return new GetOrderItemResponseDto(
                item.getProductId(),
                item.getTotalPrice(),
                item.getQuantity()
        );
    }
}
