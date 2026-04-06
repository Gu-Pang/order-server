package org.gupang.order.presentiation.dto;

import org.gupang.order.application.dto.OrderResult;

import java.util.List;
import java.util.UUID;

public record OrderResponse(
        UUID orderId,
        String status,
        String message,
        String receiverAddress,
        String receiverDetailAddress,
        String supplierAddress,
        String supplierDetailAddress,
        List<OrderItemResponse> items
) {
    public static OrderResponse from(OrderResult result) {
        return new OrderResponse(
                result.orderId(),
                result.status(),
                result.message(),
                result.receiverAddress(),
                result.receiverDetailAddress(),
                result.supplierAddress(),
                result.supplierDetailAddress(),
                result.items().stream()
                        .map(item -> new OrderItemResponse(
                                item.productId(),
                                item.totalPrice(),
                                item.quantity()
                        ))
                        .toList()
        );
    }
}

record OrderItemResponse(UUID productId, Long totalPrice, Integer quantity) {}
