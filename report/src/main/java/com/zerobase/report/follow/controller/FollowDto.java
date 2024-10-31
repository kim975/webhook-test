package com.zerobase.report.follow.controller;

import com.zerobase.report.follow.application.FollowFacadeDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class FollowDto {

    @Getter
    @Setter
    public static class RegisterFollowRequest {

        private String followUserNickName;
    }

    @Getter
    @Setter
    public static class UnfollowRequest {

        private String followUserNickName;
    }

    @Getter
    @Setter
    @ToString
    @Builder
    public static class FollowUserInfoResponse {

        private String userNickName;

        public static FollowUserInfoResponse fromDto(FollowFacadeDto.FollowUserInfoResponse dto) {
            return FollowUserInfoResponse.builder()
                .userNickName(dto.getUserNickname())
                .build();
        }
    }
}
