package org.gupang.order.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository {
    Order save(Order order);
    Order findById(UUID orderId);
    Page<Order> findAllByOrder(Pageable pageable);
}
