package org.gupang.order.application.dto;

import org.gupang.order.domain.OrderItem;

import java.util.UUID;

public record OrderItemResult(
        UUID productId,
        Long totalPrice,
        Integer quantity
) {
    public static OrderItemResult from(OrderItem item) {
        return new OrderItemResult(
                item.getProductId(),
                item.getTotalPrice(),
                item.getQuantity()
        );
    }
}
