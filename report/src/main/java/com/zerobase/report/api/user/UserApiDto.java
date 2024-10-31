package com.zerobase.report.api.user;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


public class UserApiDto {

    @Getter
    @Setter
    @ToString
    public static class UserResponse {

        private String result;
        private UserDetail data;
        private String message;
        private String errorCode;
    }

    @Getter
    @Setter
    @ToString
    public static class UserListResponse {

        private String result;
        private List<UserDetail> data;
        private String message;
        private String errorCode;
    }

    @Getter
    @Setter
    @ToString
    public static class UserDetail {

        private Long id;
        private String loginId;
        private String name;
        private String nickname;
        private String email;
        private String phoneNumber;
        private String userUuid;
    }
}
