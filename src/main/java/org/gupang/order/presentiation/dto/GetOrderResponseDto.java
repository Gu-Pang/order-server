package org.gupang.order.presentiation.dto;

import org.gupang.order.domain.Order;

import java.util.List;
import java.util.UUID;

public record GetOrderResponseDto(
        UUID orderId,
        UUID supplierId,
        UUID receiverId,
        String status,
        String address,
        String detailAddress,
        String message,
        List<GetOrderItemResponseDto> items
) {
    public static GetOrderResponseDto from(Order order) {
        return new GetOrderResponseDto(
                order.getOrderId(),
                order.getSupplierId(),
                order.getReceiverId(),
                order.getStatus().name(),
                order.getDeliveryInfo().getAddress(),
                order.getDeliveryInfo().getDetailAddress(),
                order.getMessage(),
                order.getOrderItems().stream()
                        .map(GetOrderItemResponseDto::from)
                        .toList()
        );
    }
}
