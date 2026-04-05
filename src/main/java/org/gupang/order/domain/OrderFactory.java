package org.gupang.order.domain;

import org.gupang.order.application.dto.OrderRawData;
import org.gupang.order.presentiation.dto.PostOrderRequestDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderFactory {

    public Order createFrom(PostOrderRequestDto dto, OrderRawData rawData) {
        DeliveryInfo shippingInfo = new DeliveryInfo(
                dto.address(),
                dto.detailAddress()
        );

        DeliveryInfo supplierSnapshot = new DeliveryInfo(
                rawData.companyInfo().companyAddress(),
                rawData.companyInfo().companyAddressDetail()
        );

        List<OrderItem> items = dto.orderItems().stream()
                .map(itemDto -> OrderItem.createOrderItem(
                        itemDto.productId(),
                        itemDto.totalPrice(),
                        itemDto.quantity()
                ))
                .toList();

        return Order.createOrder(
                rawData.companyInfo().companyId(),
                dto.receiverId(),
                dto.message(),
                shippingInfo,
                supplierSnapshot,
                items
        );
    }

    public DeliveryInfo createDeliveryInfo(String address, String detailAddress) {
        return new DeliveryInfo(address, detailAddress);
    }

}