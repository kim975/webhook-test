package com.zerobase.user.point.domain.repository;

import com.zerobase.user.point.domain.model.PointEntity;
import com.zerobase.user.point.domain.model.PointHistoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PointStoreImpl implements PointStore {

    private final PointHistoryRepository pointHistoryRepository;
    private final PointRepository pointRepository;

    @Override
    public PointEntity store(PointEntity point) {
        return pointRepository.save(point);
    }

    @Override
    public PointHistoryEntity store(PointHistoryEntity pointHistory) {
        return pointHistoryRepository.save(pointHistory);
    }
}
