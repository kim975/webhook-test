package com.zerobase.report.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReportErrorCode implements ErrorCode {

    NOT_FOUND_REPORT("E14001", "독서록을 찾을 수 없습니다.");

    private final String errorCode;
    private final String errorMessage;

}
