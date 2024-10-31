package com.zerobase.report.exception;

public class ApiException extends BaseException{

    private final ErrorCode errorCode;

    public ApiException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }

    public ApiException(ErrorCode errorCode, String message) {
        super(errorCode, message);
        this.errorCode = errorCode;
    }

    public ApiException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
        this.errorCode = errorCode;
    }
}
