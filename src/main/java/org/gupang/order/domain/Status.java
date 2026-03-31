package org.gupang.order.domain;

public enum Status {

    ORDER_CREATING,      // 주문 생성 중
    ORDER_ACCEPT,        // 주문 접수
    ORDER_CANCEL,        // 주문 취소
    ORDER_COMPLETED,     // 주문 및 배송 완료

    ;
}
