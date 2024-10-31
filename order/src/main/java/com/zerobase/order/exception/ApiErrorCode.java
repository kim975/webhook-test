package com.zerobase.order.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiErrorCode implements ErrorCode {

    COMMON_API_ERROR("E11001", "API 에러가 발생했습니다."),
    NOT_FOUND_BOOK("E11002", "일치하는 도서 정보가 없습니다."),
    IS_NOT_UNIQUE("E11003", "도서정보가 유일하지 않습니다.");

    private final String errorCode;
    private final String errorMessage;

}
