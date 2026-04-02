package org.gupang.order.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gupang.common.entity.BaseEntity;
import org.gupang.common.exception.CustomException;
import org.gupang.common.exception.ErrorCode;
import org.gupang.order.exception.OrderErrorCode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(name = "P_Order")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderId;

    @Column(nullable = false)
    private UUID supplierId;

    @Column(nullable = false)
    private UUID receiverId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    private String message;

    @Embedded
    private DeliveryInfo deliveryInfo;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderItem> orderItems = new ArrayList<>();

    // 정적 팩토리 메서드 객체 생성 Build로 만들까?
    public static Order createOrder(UUID supplierId,UUID receiverId, String message,DeliveryInfo deliveryInfo,List<OrderItem> items){
        if(supplierId==null||receiverId==null||message==null||items==null || deliveryInfo==null){
            throw new CustomException(ErrorCode.INVALID_INPUT_VALUE);
        }
        Order order = new Order();
        order.supplierId = supplierId;
        order.receiverId = receiverId;
        order.status = Status.ORDER_ACCEPT;
        order.deliveryInfo = deliveryInfo;
        order.message = message;

        for (OrderItem item : items) {
            order.addOrderItem(item);
        }

        return order;
    }

    private void addOrderItem(OrderItem item){
        this.orderItems.add(item);
        item.setOrder(this);
    } // 이거 왜 이렇게 쓰는지 정확히 알아보기

    // 취소 메서드
    public void cancel(){
        if(this.status != Status.ORDER_ACCEPT){
            throw new CustomException(OrderErrorCode.ORDER_ALREADY_IN_TRANSIT);
        }
        this.status = Status.ORDER_CANCEL;
    }

}
