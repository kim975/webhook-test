package com.zerobase.report.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BasicErrorCode implements ErrorCode {

    COMMON_SYSTEM_ERROR("E01001", "시스템 에러가 발생했습니다."),
    EXPIRED_TOKEN("E01002", "로그인 토큰이 만료 되었습니다."),
    FORBIDDEN("E01003", "권한이 없습니다.");

    private final String errorCode;
    private final String errorMessage;

}
