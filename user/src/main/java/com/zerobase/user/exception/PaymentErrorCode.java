package com.zerobase.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentErrorCode implements ErrorCode {


    NOT_FOUND_PG("E03001", "PG사를 찾을 수 없습니다."),
    NOT_FOUND_PAYMENT_ORDER("E03002", "결제 요청을 찾을 수 없습니다.");

    private final String errorCode;
    private final String errorMessage;

}
