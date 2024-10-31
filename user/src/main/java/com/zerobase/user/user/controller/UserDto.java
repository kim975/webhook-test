package com.zerobase.user.user.controller;

import com.zerobase.user.annotion.EncryptPassword;
import com.zerobase.user.user.service.UserCommand;
import com.zerobase.user.user.service.UserInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class UserDto {

    @Getter
    @Setter
    @ToString
    public static class SignUpRequest {

        private String loginId;

        @EncryptPassword
        private String password;
        private String name;
        private String nickname;
        private String email;
        private String phoneNumber;

        public UserCommand.SignUpUser toCommand() {
            return UserCommand.SignUpUser.builder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .nickname(nickname)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class SignInRequest {

        private String loginId;

        @EncryptPassword
        private String password;

        public UserCommand.SignInUser toCommand() {
            return UserCommand.SignInUser.builder()
                .loginId(loginId)
                .password(password)
                .build();
        }
    }

    @Getter
    @Setter
    @ToString
    @Builder
    public static class SignInResponse {

        private String nickname;

        public static UserDto.SignInResponse from(UserInfo.SignInInfo user) {
            return SignInResponse.builder()
                .nickname(user.getNickname())
                .build();
        }
    }

    @Getter
    @Setter
    @ToString
    @Builder
    public static class UserDetailResponse {

        private String loginId;
        private String name;
        private String nickname;
        private String email;
        private String phoneNumber;

        public static UserDto.UserDetailResponse from(UserInfo.UserDetail userDetail) {
            return UserDto.UserDetailResponse.builder()
                .loginId(userDetail.getLoginId())
                .name(userDetail.getName())
                .nickname(userDetail.getNickname())
                .email(userDetail.getEmail())
                .phoneNumber(userDetail.getPhoneNumber())
                .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class ModifyUserRequest {

        private String nickname;
        private String email;
        private String phoneNumber;

        @EncryptPassword
        private String password;

        public UserCommand.ModifyUser toCommand(String userUuid) {
            return UserCommand.ModifyUser.builder()
                .userUuid(userUuid)
                .nickname(nickname)
                .email(email)
                .phoneNumber(phoneNumber)
                .password(password)
                .build();
        }
    }

    @Getter
    @Setter
    @ToString
    @Builder
    public static class ModifyUserResponse {

        private String nickname;
        private String email;
        private String phoneNumber;

        public static UserDto.ModifyUserResponse from(UserInfo.UserDetail user) {
            return UserDto.ModifyUserResponse.builder()
                .nickname(user.getNickname())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
        }
    }
}
