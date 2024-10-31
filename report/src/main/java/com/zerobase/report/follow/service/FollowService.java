package com.zerobase.report.follow.service;

import com.zerobase.report.exception.BaseException;
import com.zerobase.report.exception.FollowErrorCode;
import com.zerobase.report.follow.domain.model.FollowEntity;
import com.zerobase.report.follow.domain.repository.FollowReader;
import com.zerobase.report.follow.domain.repository.FollowStore;
import com.zerobase.report.follow.service.FollowCommand.Main;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowReader followReader;
    private final FollowStore followStore;

    public void registerFollow(Main command) {

        if (followReader.isFollowed(command.getUserId(), command.getFollowUserId())) {
            throw new BaseException(FollowErrorCode.ALREADY_FOLLOW);
        }

        followStore.store(command.toEntity());
    }

    public void unfollow(Main command) {

        FollowEntity follow = followReader.getFollow(command.getUserId(), command.getFollowUserId());
        followStore.delete(follow);
    }

    public List<FollowInfo> getFollowList(Long userId) {
        return followReader.getFollows(userId)
            .stream()
            .map(FollowInfo::fromEntity)
            .toList();
    }

    public List<FollowInfo> getFollowerList(Long userId) {
        return followReader.getFollowers(userId)
            .stream()
            .map(FollowInfo::fromEntity)
            .toList();
    }
}
