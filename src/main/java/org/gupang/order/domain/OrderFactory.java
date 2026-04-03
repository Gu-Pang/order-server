package org.gupang.order.domain;

import org.gupang.order.domain.dto.OrderCompanyInfo;
import org.gupang.order.presentiation.dto.PostOrderRequestDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderFactory {

    public Order createFrom(PostOrderRequestDto dto, OrderCompanyInfo companyInfo, List<OrderItem> items) {
        DeliveryInfo companyDeliveryInfo = new DeliveryInfo(
                companyInfo.companyAddress(),
                companyInfo.companyAddressDetail()
        );

        return Order.createOrder(
                companyInfo.companyId(),
                dto.receiverId(),
                dto.message(),
                companyDeliveryInfo,
                items
        );
    }
}