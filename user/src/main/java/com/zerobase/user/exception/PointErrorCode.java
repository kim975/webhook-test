package com.zerobase.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PointErrorCode implements ErrorCode {


    NOT_FOUND_POINT_USER("E04001", "포인트를 가진 사용자를 찾을 수 없습니다.");

    private final String errorCode;
    private final String errorMessage;

}
