package com.zerobase.user.point.service.payment;

import com.zerobase.user.point.domain.model.PaymentMethod;
import com.zerobase.user.point.domain.model.payment.PointPaymentOrderEntity;
import com.zerobase.user.point.domain.model.payment.PayStatus;
import com.zerobase.user.point.domain.model.payment.PaymentTransactionEntity;
import com.zerobase.user.point.domain.model.payment.TossPaymentTransactionEntity;
import com.zerobase.user.point.service.PaymentInfo;
import com.zerobase.user.point.service.PaymentInfo.PayPointCharge;
import com.zerobase.user.util.TokenGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TossPayment implements Payment, PaymentApiCaller {

    @Override
    public boolean isSupport(PaymentMethod paymentMethod) {
        return PaymentMethod.TOSS == paymentMethod;
    }

    @Override
    public PaymentTransactionEntity makeTransactionEntity(PointPaymentOrderEntity pointPaymentOrder) {
        return TossPaymentTransactionEntity.builder()
            .pointPaymentOrderId(pointPaymentOrder.getId())
            .paymentKey(TokenGenerator.getToken())
            .orderId(TokenGenerator.getToken())
            .amount(pointPaymentOrder.getPaymentAmount())
            .status(PayStatus.IN_PROGRESS)
            .build();
    }

    @Override
    public PayPointCharge makePaymentInfoFromTransactionEntity(PaymentTransactionEntity paymentTransaction) {
        TossPaymentTransactionEntity tossPaymentTransactionEntity = (TossPaymentTransactionEntity) paymentTransaction;
        return PaymentInfo.PayPointCharge.builder()
            .id(tossPaymentTransactionEntity.getId())
            .pointPaymentOrderId(tossPaymentTransactionEntity.getPointPaymentOrderId())
            .paymentUuid(tossPaymentTransactionEntity.getTransactionUuid())
            .paymentAmount(tossPaymentTransactionEntity.getAmount())
            .payStatus(tossPaymentTransactionEntity.getStatus())
            .build();
    }

    @Override
    public PaymentTransactionEntity pay(PaymentTransactionEntity entity) {
        // PG 연동 대신 log로 대체
        TossPaymentTransactionEntity tossEntity = (TossPaymentTransactionEntity) entity;

        log.info(tossEntity.toString());

        return TossPaymentTransactionEntity.builder()
            .pointPaymentOrderId(tossEntity.getPointPaymentOrderId())
            .paymentKey(tossEntity.getPaymentKey())
            .orderId(tossEntity.getOrderId())
            .amount(tossEntity.getAmount())
            .status(PayStatus.COMPLETE)
            .build();

    }

    @Override
    public void initPaymentOrder(PaymentTransactionEntity entity) {
        // PG 연동 대신 log로 대체
        TossPaymentTransactionEntity tossEntity = (TossPaymentTransactionEntity) entity;

        log.info(tossEntity.toString());
    }
}
