package com.zerobase.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {


    DUPLICATE_EMAIL("E02001", "이메일이 중복 됩니다."),
    DUPLICATE_NICKNAME("E02002", "닉네임이 중복 됩니다."),
    DUPLICATE_PHONE_NUMBER("E02003", "휴대폰 번호가 중복 됩니다."),
    DUPLICATE_LOGIN_ID("E02004", "아이디가 중복 됩니다."),
    WRONG_ID_OR_PASSWORD("E02005", "아이디 또는 비밀번호가 틀립니다."),
    LEAVED_USER("E02006", "탈퇴한 회원 입니다."),
    NOT_FOUND_USER("E02007", "사용자를 찾을 수 없습니다.");

    private final String errorCode;
    private final String errorMessage;

}
