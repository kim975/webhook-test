package com.zerobase.user.point.domain.repository;

import com.zerobase.user.exception.BaseException;
import com.zerobase.user.exception.PointErrorCode;
import com.zerobase.user.point.domain.model.PointEntity;
import com.zerobase.user.point.domain.repository.PointReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PointReaderImpl implements PointReader {

    private final PointRepository pointRepository;

    @Override
    public PointEntity getPointByUser(Long userId) {
        return pointRepository.findByUserId(userId)
            .orElseThrow(() -> new BaseException(PointErrorCode.NOT_FOUND_POINT_USER));
    }
}
