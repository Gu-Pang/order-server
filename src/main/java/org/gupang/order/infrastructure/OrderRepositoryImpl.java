package org.gupang.order.infrastructure;

import lombok.RequiredArgsConstructor;
import org.gupang.order.domain.Order;
import org.gupang.order.domain.OrderRepository;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order save(Order order) {
        return orderJpaRepository.save(order);
    }
}
