package org.gupang.order.application;

import lombok.RequiredArgsConstructor;
import org.gupang.order.application.dto.OrderDto;
import org.gupang.order.domain.*;
import org.gupang.order.infrastructure.dto.CompanyResponseDto;
import org.gupang.order.presentiation.dto.PostOrderRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;
    private final OrderFactory orderFactory;

    @Transactional
    public void createOrder(PostOrderRequestDto postOrderRequestDto) {
        CompanyResponseDto companyInfo = orderValidator.validateOrder(postOrderRequestDto.orderItems());

        List<OrderItem> orderItems = postOrderRequestDto.orderItems().stream()
                .map(OrderItem::from)
                .toList();

        Order order = orderFactory.createFrom(postOrderRequestDto, companyInfo, orderItems);

        orderRepository.save(order);
    }

    public OrderDto getOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId);
        return OrderDto.from(order);
    }

    public void cancelOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId);
        order.cancel();
    }
}
