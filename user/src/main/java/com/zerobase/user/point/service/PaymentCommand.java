package com.zerobase.user.point.service;

import com.zerobase.user.point.domain.model.PaymentMethod;
import com.zerobase.user.point.domain.model.PaymentStatus;
import com.zerobase.user.point.domain.model.payment.PointPaymentOrderEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class PaymentCommand {

    @Getter
    @Setter
    @Builder
    @ToString
    public static class InitPaymentOrder {

        private Long userId;
        private Long paymentAmount;
        private PaymentMethod paymentMethod;

        public PointPaymentOrderEntity toEntity() {
            return PointPaymentOrderEntity.builder()
                .userId(userId)
                .paymentAmount(paymentAmount)
                .paymentMethod(paymentMethod)
                .paymentStatus(PaymentStatus.IN_PROGRESS)
                .build();
        }

    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class PayPointCharge {

        private Long pointPaymentOrderId;
        private PaymentMethod paymentMethod;
    }
}
