package com.zerobase.user.exception;

public class LockException extends BaseException {

    public LockException(ErrorCode errorCode) {
        super(errorCode);
    }

    public LockException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public LockException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

}
