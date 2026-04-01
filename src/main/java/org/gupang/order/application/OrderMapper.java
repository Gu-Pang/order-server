package org.gupang.order.application;

import org.gupang.order.domain.OrderItem;
import org.gupang.order.presentiation.dto.OrderItemRequestDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
    public List<OrderItem> toOrderItemList(List<OrderItemRequestDto> itemDtos){
        return itemDtos.stream().map(dto -> OrderItem.createOrderItem(
                dto.productId(),
                dto.totalPrice(),
                dto.quantity()
        ))
                .toList();
    }
}
