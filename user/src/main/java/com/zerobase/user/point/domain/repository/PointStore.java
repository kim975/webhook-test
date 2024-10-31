package com.zerobase.user.point.domain.repository;

import com.zerobase.user.point.domain.model.PointEntity;
import com.zerobase.user.point.domain.model.PointHistoryEntity;

public interface PointStore {

    PointEntity store(PointEntity point);

    PointHistoryEntity store(PointHistoryEntity pointHistory);
}
