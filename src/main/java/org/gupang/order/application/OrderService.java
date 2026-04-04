package org.gupang.order.application;

import lombok.RequiredArgsConstructor;
import org.gupang.order.application.dto.OrderRawData;
import org.gupang.order.application.dto.OrderResult;
import org.gupang.order.domain.Order;
import org.gupang.order.domain.OrderFactory;
import org.gupang.order.domain.OrderItem;
import org.gupang.order.domain.OrderRepository;
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
    private final OrderInfoProvider orderInfoProvider;

    @Transactional
    public UUID createOrder(PostOrderRequestDto postOrderRequestDto) {
    List<UUID> productIds = postOrderRequestDto.orderItems().stream()
            .map(item -> item.productId())
            .toList();
        OrderRawData rawData = orderInfoProvider.getOrderRawData(productIds);

        orderValidator.validate(postOrderRequestDto, rawData);
        List<OrderItem> orderItems = postOrderRequestDto.orderItems().stream()
                .map(OrderItem ::from)
                .toList();

        Order order = orderFactory.createFrom(postOrderRequestDto,rawData,orderItems);

        Order savedOrder = orderRepository.save(order);

        return savedOrder.getOrderId();
    }

    @Transactional
    public OrderResult getOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId);
        return OrderResult.from(order);
    }

    public void cancelOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId);
        order.cancel();
    }
}
