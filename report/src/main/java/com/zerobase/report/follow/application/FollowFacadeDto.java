package com.zerobase.report.follow.application;

import com.zerobase.report.api.user.UserApiDto.UserDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class FollowFacadeDto {

    @Getter
    @Setter
    @Builder
    @ToString
    public static class MainRequest {

        private String userUuid;
        private String followUserNickName;

    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class FollowUserInfoResponse {

        private String userNickname;

        public static FollowUserInfoResponse from(UserDetail user) {
            return FollowUserInfoResponse.builder()
                .userNickname(user.getNickname())
                .build();
        }
    }

}
