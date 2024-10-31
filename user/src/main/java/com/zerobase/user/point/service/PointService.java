package com.zerobase.user.point.service;

import com.zerobase.user.aop.PointLock;
import com.zerobase.user.point.domain.model.PointEntity;
import com.zerobase.user.point.domain.model.PointHistoryEntity;
import com.zerobase.user.point.domain.model.PointType;
import com.zerobase.user.point.domain.repository.PointReader;
import com.zerobase.user.point.domain.repository.PointStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointReader pointReader;
    private final PointStore pointStore;

    @PointLock
    public PointInfo.AddPoint addPoint(PointCommand.AddPoint command) {

        PointEntity point = pointReader.getPointByUser(command.getUserId());

        //포인트 히스토리 생성
        PointHistoryEntity pointHistory = PointHistoryEntity.builder()
            .point(point)
            .pointPaymentOrderId(command.getPointPaymentOrderId())
            .beforePoint(point.getPoint())
            .changePoint(command.getChangePoint())
            .afterPoint(point.getPoint() + command.getChangePoint())
            .pointType(PointType.CHARGE)
            .build();

        //포인트 충전
        point.setPoint(point.getPoint() + command.getChangePoint());

        PointEntity storedPoint = pointStore.store(point);
        pointStore.store(pointHistory);

        return PointInfo.AddPoint.from(storedPoint);
    }

    public void initPoint(Long id) {
        PointEntity point = PointEntity.builder()
            .userId(id)
            .point(0L)
            .build();

        pointStore.store(point);
    }
}
