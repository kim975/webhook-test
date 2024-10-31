package com.zerobase.report.follow.domain.repository;

import com.zerobase.report.follow.domain.model.FollowEntity;
import java.util.List;

public interface FollowReader {

    FollowEntity getFollow(Long userId, Long followUserId);

    boolean isFollowed(Long userId, Long followUserId);

    List<FollowEntity> getFollows(Long userId);

    List<FollowEntity> getFollowers(Long userId);
}
