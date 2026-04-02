package org.gupang.order.application;

import lombok.RequiredArgsConstructor;
import org.gupang.order.application.dto.OrderDto;
import org.gupang.order.domain.DeliveryInfo;
import org.gupang.order.domain.Order;
import org.gupang.order.domain.OrderItem;
import org.gupang.order.domain.OrderRepository;
import org.gupang.order.presentiation.dto.PostOrderRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;

    public OrderDto createOrder(PostOrderRequestDto postOrderRequestDto){
        orderValidator.validateOrder(postOrderRequestDto.supplierId(),postOrderRequestDto.orderItems());

        List<OrderItem> orderItems = postOrderRequestDto.orderItems().stream()
                .map(OrderItem::from)
                .toList();

        DeliveryInfo deliveryInfo = new DeliveryInfo(
                postOrderRequestDto.address(),
                postOrderRequestDto.detailAddress()
        );

        Order order = Order.createOrder(
                postOrderRequestDto.supplierId(),
                postOrderRequestDto.receiverId(),
                postOrderRequestDto.message(),
                deliveryInfo,
                orderItems
        );
        Order savedOrder = orderRepository.save(order);
        return OrderDto.from(savedOrder);
    }

    public OrderDto getOrder(UUID orderId){
        Order order = orderRepository.findById(orderId);
        return  OrderDto.from(order);
    }

}
