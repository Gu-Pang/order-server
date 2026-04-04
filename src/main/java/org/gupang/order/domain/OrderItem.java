package org.gupang.order.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gupang.order.presentiation.dto.PostOrderItemRequestDto;

import java.util.UUID;

@Getter
@Table(name = "P_ORDER_ITEM")
@Entity
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID orderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;

    @Column(nullable = false)
    private UUID productId;

    @Column(nullable = false)
    private Long totalPrice;

    @Column(nullable = false)
    private Integer quantity;

    public static OrderItem createOrderItem( UUID productId, Long totalPrice, Integer quantity){
        OrderItem orderItem = new OrderItem();
        orderItem.productId = productId;
        orderItem.totalPrice = totalPrice;
        orderItem.quantity = quantity;
        return orderItem;
    }

    public static OrderItem from(PostOrderItemRequestDto dto) {
        return OrderItem.createOrderItem(
                dto.productId(),
                dto.totalPrice(),
                dto.quantity()
        );
    }
    public void setOrder(Order order) {
        this.order = order;
    }
}
