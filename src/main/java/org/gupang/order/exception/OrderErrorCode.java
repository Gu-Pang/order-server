package org.gupang.order.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.gupang.common.exception.BaseErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum OrderErrorCode implements BaseErrorCode {
    ORDER_ALREADY_IN_TRANSIT(HttpStatus.CONFLICT,"배송 중인 주문은 취소할 수 없습니다."),
    SUPPLIER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 업체입니다."),
    INVALID_ORDER_QUANTITY(HttpStatus.BAD_REQUEST, "주문 수량은 1개 이상이어야 합니다."),
    INVALID_PRODUCT_FOR_SUPPLIER(HttpStatus.BAD_REQUEST, "해당 업체의 상품이 아니거나 존재하지 않는 상품이 포함되어 있습니다."),
    OUT_OF_STOCK(HttpStatus.CONFLICT, "상품의 재고가 부족합니다."),
    ORDER_IS_NOT_FOUND(HttpStatus.NOT_FOUND,"존재하지 않는 주문입니다.")

    ;

    private final HttpStatus httpStatus;
    private final String message;


    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}