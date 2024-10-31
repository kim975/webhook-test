package com.zerobase.user.point.application;

import com.zerobase.user.point.domain.model.PaymentMethod;
import com.zerobase.user.point.domain.model.PaymentStatus;
import com.zerobase.user.point.service.PaymentCommand;
import com.zerobase.user.point.service.PaymentInfo.InitPointCharge;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class PointFacadeDto {

    @Getter
    @Setter
    @Builder
    @ToString
    public static class InitPointChargeRequest {

        private String userUuid;
        private Long paymentAmount;
        private PaymentMethod paymentMethod;

        public PaymentCommand.InitPaymentOrder toCommand(Long userId) {
            return PaymentCommand.InitPaymentOrder.builder()
                .userId(userId)
                .paymentAmount(paymentAmount)
                .paymentMethod(paymentMethod)
                .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class InitPointChargeResponse {

        private Long pointPaymentOrderId;
        private String paymentUuid;
        private Long paymentAmount;
        private PaymentMethod paymentMethod;
        private PaymentStatus paymentStatus;

        public static InitPointChargeResponse from(InitPointCharge paymentInfo) {
            return InitPointChargeResponse.builder()
                .pointPaymentOrderId(paymentInfo.getPointPaymentOrderId())
                .paymentAmount(paymentInfo.getPaymentAmount())
                .paymentMethod(paymentInfo.getPaymentMethod())
                .paymentStatus(paymentInfo.getPaymentStatus())
                .paymentUuid(paymentInfo.getPaymentUuid())
                .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class AddPointRequest {

        private String userUuid;
        private Long pointPaymentOrderId;
        private PaymentMethod paymentMethod;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class AddPointResponse {

        private Long currentPoint;

        public static AddPointResponse from(Long currentPoint) {
            return AddPointResponse.builder()
                .currentPoint(currentPoint)
                .build();
        }

    }
}
