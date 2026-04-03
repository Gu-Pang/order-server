package org.gupang.order.domain;

import org.gupang.order.infrastructure.dto.CompanyResponseDto;
import org.gupang.order.presentiation.dto.PostOrderRequestDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderFactory {

    public Order createFrom(PostOrderRequestDto dto, CompanyResponseDto companyInfo, List<OrderItem> items) {
        DeliveryInfo companyDeliveryInfo = new DeliveryInfo(
                companyInfo.company_address(),
                companyInfo.company_address_detail()
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