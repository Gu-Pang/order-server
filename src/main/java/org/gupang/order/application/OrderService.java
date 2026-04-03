package org.gupang.order.application;

import lombok.RequiredArgsConstructor;
import org.gupang.order.domain.Order;
import org.gupang.order.domain.OrderFactory;
import org.gupang.order.domain.OrderItem;
import org.gupang.order.domain.OrderRepository;
import org.gupang.order.presentiation.dto.GetOrderResponseDto;
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

    public void createOrder(PostOrderRequestDto postOrderRequestDto) {

        UUID supplierId = orderValidator.validateAndGetSupplierId(postOrderRequestDto);
        saveOrder(postOrderRequestDto, supplierId);
    }

    @Transactional
    public void saveOrder(PostOrderRequestDto postOrderRequestDto,UUID supplierId) {
        List<OrderItem> orderItems = postOrderRequestDto.orderItems().stream()
                .map(OrderItem::from)
                .toList();

        Order order = orderFactory.createFrom(postOrderRequestDto, supplierId, orderItems);
        orderRepository.save(order);
    }

    @Transactional
    public GetOrderResponseDto getOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId);
        return GetOrderResponseDto.from(order);
    }
}
