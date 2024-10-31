package com.zerobase.user.point.domain.repository;

import com.zerobase.user.point.domain.model.PointEntity;

public interface PointReader {

    PointEntity getPointByUser(Long userId);
}
