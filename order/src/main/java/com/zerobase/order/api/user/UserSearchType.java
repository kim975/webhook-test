package com.zerobase.order.api.user;

import com.zerobase.order.exception.BaseException;
import com.zerobase.order.exception.BasicErrorCode;
import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserSearchType {

    NICKNAME("nickname"),
    USER_UUID("userUuid"),
    USER_ID("userId"),
    LOGIN_ID("loginId");

    private final String searchType;

    public static UserSearchType find(String searchType) {
        return Arrays.stream(UserSearchType.values())
            .filter(search -> search.searchType.equals(searchType))
            .findAny()
            .orElseThrow(() -> new BaseException(BasicErrorCode.COMMON_SYSTEM_ERROR));
    }
}
