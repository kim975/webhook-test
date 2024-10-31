package com.zerobase.report.exception;

public class JwtCustomException extends BaseException {

    public JwtCustomException(ErrorCode errorCode) {
        super(errorCode);
    }

    public JwtCustomException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public JwtCustomException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
