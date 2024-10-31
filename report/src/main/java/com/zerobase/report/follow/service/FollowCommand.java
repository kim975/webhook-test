package com.zerobase.report.follow.service;

import com.zerobase.report.follow.domain.model.FollowEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class FollowCommand {

    @Getter
    @Setter
    @Builder
    @ToString
    public static class Main {

        private Long userId;
        private Long followUserId;

        public FollowEntity toEntity() {
            return FollowEntity.builder()
                .userId(userId)
                .followUserId(followUserId)
                .build();
        }
    }
}
