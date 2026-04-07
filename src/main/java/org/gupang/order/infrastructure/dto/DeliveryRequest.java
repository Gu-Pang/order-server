package org.gupang.order.infrastructure.dto;

import org.gupang.order.domain.Order;

import java.util.UUID;

public record DeliveryRequest(
        UUID orderId,
        UUID supplierId,
        UUID receiverId,
        String address,
        String addressDetail,
        String recipientName
) {

    public static DeliveryRequest from(Order order) {
        return new DeliveryRequest(
                order.getOrderId(),
                order.getSupplierId(),
                order.getReceiverId(),
                order.getReceiverInfo().getAddress(),
                order.getReceiverInfo().getDetailAddress(),
                null // TODO: order 생성 시 수령자 이름을 받고 있지 않아서 null, 추후 수정 필요
        );
    }

}
