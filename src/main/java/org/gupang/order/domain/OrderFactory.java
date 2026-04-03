package org.gupang.order.domain;

import org.gupang.order.domain.dto.OrderCompanyInfo;
import org.gupang.order.presentiation.dto.PostOrderRequestDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class OrderFactory {

    public Order createFrom(PostOrderRequestDto dto, UUID supplierId, List<OrderItem> items) {
        DeliveryInfo companyDeliveryInfo = new DeliveryInfo(
                dto.address(),
                dto.detailAddress()
        );

        return Order.createOrder(
                supplierId,
                dto.receiverId(),
                dto.message(),
                companyDeliveryInfo,
                items
        );
    }
}