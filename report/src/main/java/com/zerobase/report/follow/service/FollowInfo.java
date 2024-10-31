package com.zerobase.report.follow.service;

import com.zerobase.report.follow.domain.model.FollowEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class FollowInfo {

    private Long id;
    private Long userId;
    private Long followUserId;

    public static FollowInfo fromEntity(FollowEntity entity) {
        return FollowInfo.builder()
            .id(entity.getId())
            .userId(entity.getUserId())
            .followUserId(entity.getFollowUserId())
            .build();
    }
}
