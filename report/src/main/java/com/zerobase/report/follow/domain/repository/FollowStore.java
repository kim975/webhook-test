package com.zerobase.report.follow.domain.repository;

import com.zerobase.report.follow.domain.model.FollowEntity;

public interface FollowStore {

    FollowEntity store(FollowEntity entity);

    void delete(FollowEntity follow);
}
