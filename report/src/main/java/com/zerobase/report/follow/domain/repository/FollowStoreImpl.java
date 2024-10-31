package com.zerobase.report.follow.domain.repository;

import com.zerobase.report.follow.domain.model.FollowEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FollowStoreImpl implements FollowStore {

    private final FollowRepository followRepository;

    @Override
    public FollowEntity store(FollowEntity entity) {
        return followRepository.save(entity);
    }

    @Override
    public void delete(FollowEntity follow) {
        followRepository.delete(follow);
    }
}
