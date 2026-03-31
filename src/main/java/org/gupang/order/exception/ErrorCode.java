package org.gupang.order.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.gupang.common.exception.BaseErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode implements BaseErrorCode {
    ORDER_ALREADY_IN_TRANSIT(HttpStatus.CONFLICT, "배송 중인 주문은 취소할 수 없습니다."),
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