package org.gupang.order.domain;

import org.gupang.order.application.dto.OrderRawData;
import org.gupang.order.presentiation.dto.PostOrderRequestDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class OrderFactory {

    public Order createFrom(PostOrderRequestDto dto, OrderRawData rawData, List<OrderItem> items) {
        DeliveryInfo shippingInfo = new DeliveryInfo(
                dto.address(),
                dto.detailAddress()
        );

        DeliveryInfo supplierSnapshot = new DeliveryInfo(
                rawData.companyInfo().companyAddress(),
                rawData.companyInfo().companyAddressDetail()
        );

        return Order.createOrder(
                rawData.companyInfo().companyId(),
                dto.receiverId(),
                dto.message(),
                shippingInfo,
                supplierSnapshot,
                items
        );
    }
}