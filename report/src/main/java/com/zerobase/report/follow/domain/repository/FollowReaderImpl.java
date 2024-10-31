package com.zerobase.report.follow.domain.repository;

import com.zerobase.report.exception.BaseException;
import com.zerobase.report.exception.FollowErrorCode;
import com.zerobase.report.follow.domain.model.FollowEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FollowReaderImpl implements FollowReader {

    private final FollowRepository followRepository;

    @Override
    public FollowEntity getFollow(Long userId, Long followUserId) {
        return followRepository.findByUserIdAndFollowUserId(userId, followUserId).orElseThrow(() -> new BaseException(FollowErrorCode.NOT_FOUND_FOLLOW));
    }

    @Override
    public boolean isFollowed(Long userId, Long followUserId) {
        return followRepository.findByUserIdAndFollowUserId(userId, followUserId).isPresent();
    }

    @Override
    public List<FollowEntity> getFollows(Long userId) {
        return followRepository.findAllByUserId(userId);
    }

    @Override
    public List<FollowEntity> getFollowers(Long userId) {
        return followRepository.findAllByFollowUserId(userId);
    }
}
