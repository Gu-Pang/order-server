package org.gupang.order.application.dto;

import org.gupang.order.domain.OrderItem;

import java.util.UUID;

public record OrderItemDto(
        UUID productId,
        Long totalPrice,
        Integer quantity
) {
    public static OrderItemDto from(OrderItem item) {
        return new OrderItemDto(
                item.getProductId(),
                item.getTotalPrice(),
                item.getQuantity()
        );
    }
}
