package com.zerobase.user.point.service.payment;

import com.zerobase.user.point.domain.model.PaymentMethod;
import com.zerobase.user.point.domain.model.payment.PointPaymentOrderEntity;
import com.zerobase.user.point.domain.model.payment.KakaoPaymentTransactionEntity;
import com.zerobase.user.point.domain.model.payment.PayStatus;
import com.zerobase.user.point.domain.model.payment.PaymentTransactionEntity;
import com.zerobase.user.point.service.PaymentInfo;
import com.zerobase.user.point.service.PaymentInfo.PayPointCharge;
import com.zerobase.user.util.TokenGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KakaoPayment implements Payment, PaymentApiCaller {

    //가맹정 코드
    private static final String CID = "가맹점1234567890";

    @Override
    public boolean isSupport(PaymentMethod paymentMethod) {
        return PaymentMethod.KAKAO == paymentMethod;
    }

    @Override
    public PaymentTransactionEntity makeTransactionEntity(PointPaymentOrderEntity pointPaymentOrder) {
        return KakaoPaymentTransactionEntity.builder()
            .pointPaymentOrderId(pointPaymentOrder.getId())
            .cid(CID)
            .tid(TokenGenerator.getToken())
            .totalAmount(pointPaymentOrder.getPaymentAmount())
            .status(PayStatus.IN_PROGRESS)
            .build();
    }

    @Override
    public PayPointCharge makePaymentInfoFromTransactionEntity(PaymentTransactionEntity paymentTransaction) {
        KakaoPaymentTransactionEntity kakaoPaymentTransactionEntity = (KakaoPaymentTransactionEntity) paymentTransaction;
        return PaymentInfo.PayPointCharge.builder()
            .id(kakaoPaymentTransactionEntity.getId())
            .pointPaymentOrderId(kakaoPaymentTransactionEntity.getPointPaymentOrderId())
            .paymentUuid(kakaoPaymentTransactionEntity.getTransactionUuid())
            .paymentAmount(kakaoPaymentTransactionEntity.getTotalAmount())
            .payStatus(kakaoPaymentTransactionEntity.getStatus())
            .build();
    }

    @Override
    public PaymentTransactionEntity pay(PaymentTransactionEntity entity) {
        // PG 연동 대신 log로 대체
        KakaoPaymentTransactionEntity kakaoEntity = (KakaoPaymentTransactionEntity) entity;

        log.info(kakaoEntity.toString());

        return KakaoPaymentTransactionEntity.builder()
            .tid(kakaoEntity.getTid())
            .cid(kakaoEntity.getCid())
            .pointPaymentOrderId(kakaoEntity.getPointPaymentOrderId())
            .totalAmount(kakaoEntity.getTotalAmount())
            .status(PayStatus.COMPLETE)
            .build();
    }

    @Override
    public void initPaymentOrder(PaymentTransactionEntity entity) {

        // PG 연동 대신 log로 대체
        KakaoPaymentTransactionEntity kakaoEntity = (KakaoPaymentTransactionEntity) entity;

        log.info(kakaoEntity.toString());
    }

}
