package com.zerobase.user.point.service;

import com.zerobase.user.point.domain.model.PointEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class PointInfo {

    @Getter
    @Setter
    @Builder
    @ToString
    public static class AddPoint {

        private Long point;

        public static PointInfo.AddPoint from(PointEntity pointEntity) {
            return PointInfo.AddPoint.builder()
                .point(pointEntity.getPoint())
                .build();
        }

    }
}
