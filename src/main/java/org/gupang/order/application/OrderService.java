package org.gupang.order.application;

import lombok.RequiredArgsConstructor;
import org.gupang.order.application.dto.OrderRawData;
import org.gupang.order.application.dto.OrderResult;
import org.gupang.order.domain.*;
import org.gupang.order.presentiation.dto.PostOrderRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        Order order = orderFactory.createFrom(postOrderRequestDto,rawData);
        Order savedOrder = orderRepository.save(order);
        return savedOrder.getOrderId();
    }

    @Transactional(readOnly = true)
    public OrderResult getOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId);
        return OrderResult.from(order);
    }

    @Transactional
    public void cancelOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId);
        order.cancel();
        // 재고 복구 로직 필요
    }

    @Transactional(readOnly = true)
    public Page<OrderResult> getOrders(Pageable pageable) {
       return orderRepository.findAllByOrder(pageable).map(OrderResult::from);
    }

    @Transactional
    public OrderResult updateOrder(UUID orderId,String address,String detailAddress, String message,UUID productId,int newQuantity) {
        Order order = orderRepository.findById(orderId);
        OrderRawData rawData = orderInfoProvider.getOrderRawData(List.of(productId));

        orderValidator.validateUpdate(productId,newQuantity,rawData);

        DeliveryInfo newInfo = orderFactory.createDeliveryInfo(address, detailAddress);
        order.updateOrder(newInfo,message,productId,newQuantity,rawData.productInfos().get(0).price());

        return OrderResult.from(order);
    }

    @Transactional
    public void startShipping(UUID orderId) {
        Order order = orderRepository.findById(orderId);
        order.startShipping();
    }

    @Transactional
    public void completeOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId);
        order.complete();
    }
}
