package com.zerobase.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BasicErrorCode implements ErrorCode {

    COMMON_SYSTEM_ERROR("E01001", "시스템 에러가 발생했습니다."),
    EXPIRED_TOKEN("E01002", "로그인 토큰이 만료 되었습니다."),
    FORBIDDEN("E01003", "권한이 없습니다."),
    COMMON_POINT_ERROR("E01004", "포인트 관련 시스템 에러가 발생했습니다."),
    DO_NOT_GET_LOCK("E01005", "락을 획득하지 못 했습니다."),
    ENCRYPT_ERROR("E01006", "암복호화 오류가 발생했습니다.");

    private final String errorCode;
    private final String errorMessage;

}
