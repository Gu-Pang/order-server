package org.gupang.order.application;

import lombok.RequiredArgsConstructor;
import org.gupang.order.domain.Order;
import org.gupang.order.domain.OrderItem;
import org.gupang.order.domain.OrderRepository;
import org.gupang.order.presentiation.dto.PostOrderRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;
    private final OrderMapper orderMapper;

    public void createOrder(PostOrderRequestDto postOrderRequestDto){
        orderValidator.validateOrder(postOrderRequestDto.supplierId(),postOrderRequestDto.orderItems());

        List<OrderItem> orderItems = orderMapper.toOrderItemList(postOrderRequestDto.orderItems());

        Order order = Order.createOrder(
                postOrderRequestDto.supplierId(),
                postOrderRequestDto.receiverId(),
                postOrderRequestDto.message(),
                orderItems
        );

        orderRepository.save(order);
    }


}
