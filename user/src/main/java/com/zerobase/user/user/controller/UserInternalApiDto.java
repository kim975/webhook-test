package com.zerobase.user.user.controller;

import com.zerobase.user.user.service.UserInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class UserInternalApiDto {

    @Getter
    @Builder
    @ToString
    public static class UserDetailResponse {

        private Long id;
        private String loginId;
        private String name;
        private String nickname;
        private String email;
        private String phoneNumber;
        private String userUuid;

        public static UserInternalApiDto.UserDetailResponse from(UserInfo.UserDetail userDetail) {
            return UserInternalApiDto.UserDetailResponse.builder()
                .id(userDetail.getId())
                .loginId(userDetail.getLoginId())
                .name(userDetail.getName())
                .nickname(userDetail.getNickname())
                .email(userDetail.getEmail())
                .phoneNumber(userDetail.getPhoneNumber())
                .userUuid(userDetail.getUserUuid())
                .build();
        }

    }

}
