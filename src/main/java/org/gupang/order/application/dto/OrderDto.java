package org.gupang.order.application.dto;

import org.gupang.order.domain.Order;

import java.util.List;
import java.util.UUID;

public record OrderDto(
        UUID orderId,
        UUID supplierId,
        UUID receiverId,
        String status,
        String address,
        String detailAddress,
        String message,
        List<OrderItemDto> items
) {

    public static OrderDto from(Order order){
        return new OrderDto(
                order.getOrderId(),
                order.getSupplierId(),
                order.getReceiverId(),
                order.getStatus().name(),
                order.getDeliveryInfo().getAddress(),
                order.getDeliveryInfo().getDetailAddress(),
                order.getMessage(),
                order.getOrderItems().stream()
                        .map(OrderItemDto::from)
                        .toList()
        );
    }
}