package org.gupang.order.infrastructure;

import lombok.RequiredArgsConstructor;
import org.gupang.common.exception.CustomException;
import org.gupang.order.domain.Order;
import org.gupang.order.domain.OrderRepository;
import org.gupang.order.exception.OrderErrorCode;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order save(Order order) {
        return orderJpaRepository.save(order);
    }

    @Override
    public Order findById(UUID orderId) {
        return orderJpaRepository.findById(orderId)
                .orElseThrow(()->new CustomException(OrderErrorCode.ORDER_IS_NOT_FOUND));
    }
}
