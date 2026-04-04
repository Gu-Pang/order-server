package org.gupang.order.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.gupang.common.exception.BaseErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum OrderErrorCode implements BaseErrorCode {
    EMPTY_ORDER_ITEM(HttpStatus.BAD_REQUEST, "주문 항목이 비어 있습니다."),
    INVALID_ORDER_QUANTITY(HttpStatus.BAD_REQUEST, "주문 수량은 1개 이상이어야 합니다."),
    RECEIVER_INFO_MISSING(HttpStatus.BAD_REQUEST, "수령인 정보(이름, 주소 등)가 누락되었습니다."),
    MULTI_SUPPLIER_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "한 주문에는 하나의 공급 업체 상품만 담을 수 있습니다."),
    PRODUCT_NOT_BELONG_TO_SUPPLIER(HttpStatus.BAD_REQUEST, "해당 업체의 상품이 아니거나 정보가 불일치합니다."),
    PRODUCT_PRICE_MISMATCH(HttpStatus.CONFLICT, "주문 시점의 가격과 서버의 현재 가격이 일치하지 않습니다."),
    OUT_OF_STOCK(HttpStatus.CONFLICT, "상품의 재고가 부족합니다."),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 주문입니다."),
    SUPPLIER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않거나 유효하지 않은 업체 정보입니다."),
    ORDER_ALREADY_CANCELLED(HttpStatus.BAD_REQUEST,"이미 취소된 주문 입니다."),
    ORDER_ALREADY_IN_TRANSIT(HttpStatus.CONFLICT,"배송 중인 주문은 취소할 수 없습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;

}