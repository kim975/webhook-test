package com.zerobase.report.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BookErrorCode implements ErrorCode {

    NOT_FOUND_BOOK("E12001", "도서 정보를 찾을 수 없습니다.");

    private final String errorCode;
    private final String errorMessage;
}
