package com.zerobase.user.user.service;

import com.zerobase.user.user.domain.model.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


public class UserCommand {

    @Getter
    @Builder
    @ToString
    public static class SignUpUser {

        private String loginId;
        private String password;
        private String name;
        private String nickname;
        private String email;
        private String phoneNumber;

        public UserEntity toEntity() {
            return UserEntity.builder()
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
    @Builder
    @ToString
    public static class SignInUser {

        private String loginId;
        private String password;

        public UserEntity toEntity() {
            return UserEntity.builder()
                .loginId(loginId)
                .password(password)
                .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class ModifyUser {

        private String userUuid;
        private String password;
        private String nickname;
        private String email;
        private String phoneNumber;

    }
}
