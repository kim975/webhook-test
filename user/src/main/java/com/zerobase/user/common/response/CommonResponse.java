package com.zerobase.user.common.response;

import com.zerobase.user.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {

    private Result result;
    private T data;
    private String message;
    private String errorCode;

    public static <T> CommonResponse<T> success() {
        return success(null);
    }

    public static <T> CommonResponse<T> success(T data) {
        return success(data, null);
    }

    public static <T> CommonResponse<T> success(T data, String message) {
        return (CommonResponse<T>) CommonResponse.builder()
                .result(Result.SUCCESS)
                .data(data)
                .message(message)
                .build();
    }

    public static CommonResponse fail(String errorMessage, String errorCode) {
        return CommonResponse.builder()
                .result(Result.FAIL)
                .message(errorMessage)
                .errorCode(errorCode)
                .build();
    }

    public static CommonResponse fail(ErrorCode errorCode) {
        return CommonResponse.builder()
                .result(Result.FAIL)
                .message(errorCode.getErrorMessage())
                .errorCode(errorCode.getErrorCode())
                .build();
    }

    public enum Result {
        SUCCESS, FAIL
    }

}
