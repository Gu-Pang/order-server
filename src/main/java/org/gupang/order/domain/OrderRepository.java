package org.gupang.order.domain;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository {
    Order save(Order order);
    Order findById(UUID orderId);
}
