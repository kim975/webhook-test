package com.zerobase.user.point.service;

import com.zerobase.user.aop.PointLockInterface;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class PointCommand {

    @Getter
    @Setter
    @Builder
    @ToString
    public static class AddPoint implements PointLockInterface {

        private Long userId;
        private Long changePoint;
        private Long pointPaymentOrderId;
    }
}
