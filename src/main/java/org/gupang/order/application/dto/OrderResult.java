package org.gupang.order.application.dto;

import org.gupang.order.domain.Order;
import java.util.List;
import java.util.UUID;



public record OrderResult(
        UUID orderId,
        UUID supplierId,
        UUID receiverId,
        String status,
        String receiverAddress,
        String receiverDetailAddress,
        String supplierAddress,
        String supplierDetailAddress,
        String message,
        List<OrderItemResult> items
) {
    public static OrderResult from(Order order){

        return new OrderResult(
                order.getOrderId(),
                order.getSupplierId(),
                order.getReceiverId(),
                order.getStatus().name(),
                order.getReceiverInfo().getAddress(),
                order.getReceiverInfo().getDetailAddress(),
                order.getSupplierInfo().getAddress(),
                order.getSupplierInfo().getDetailAddress(),
                order.getMessage(),
                order.getOrderItems().stream()
                        .map(OrderItemResult::from)
                        .toList()
        );
    }
}