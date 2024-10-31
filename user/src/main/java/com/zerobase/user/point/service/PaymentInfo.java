package com.zerobase.user.point.service;

import com.zerobase.user.point.domain.model.PaymentMethod;
import com.zerobase.user.point.domain.model.PaymentStatus;
import com.zerobase.user.point.domain.model.payment.PayStatus;
import com.zerobase.user.point.domain.model.payment.PointPaymentOrderEntity;
import com.zerobase.user.point.domain.model.payment.PaymentTransactionEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class PaymentInfo {

    @Getter
    @Setter
    @Builder
    @ToString
    public static class InitPointCharge {

        private Long pointPaymentOrderId;
        private String paymentUuid;
        private Long paymentAmount;
        private PaymentMethod paymentMethod;
        private PaymentStatus paymentStatus;


        public static InitPointCharge from(PointPaymentOrderEntity pointPaymentOrder, PaymentTransactionEntity paymentTransaction) {
            return InitPointCharge.builder()
                .pointPaymentOrderId(pointPaymentOrder.getId())
                .paymentUuid(paymentTransaction.getTransactionUuid())
                .paymentAmount(pointPaymentOrder.getPaymentAmount())
                .paymentMethod(pointPaymentOrder.getPaymentMethod())
                .paymentStatus(pointPaymentOrder.getPaymentStatus())
                .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class PayPointCharge {

        private Long id;
        private Long pointPaymentOrderId;
        private String paymentUuid;
        private Long paymentAmount;
        private PaymentMethod paymentMethod;
        private PayStatus payStatus;

    }
}
