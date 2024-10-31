package com.zerobase.user.point.service;

import com.zerobase.user.point.domain.model.PaymentStatus;
import com.zerobase.user.point.domain.model.payment.PointPaymentOrderEntity;
import com.zerobase.user.point.domain.model.payment.PaymentTransactionEntity;
import com.zerobase.user.point.domain.repository.payment.PaymentReader;
import com.zerobase.user.point.domain.repository.payment.PaymentStore;
import com.zerobase.user.point.service.PaymentInfo.InitPointCharge;
import com.zerobase.user.point.service.payment.PaymentProcessor;
import com.zerobase.user.point.service.payment.PaymentSupporter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentReader paymentReader;
    private final PaymentStore paymentStore;
    private final PaymentProcessor paymentProcessor;
    private final PaymentSupporter paymentSupporter;

    public InitPointCharge callPaymentOrder(PaymentCommand.InitPaymentOrder command) {

        PointPaymentOrderEntity storePointPaymentOrderEntity = paymentStore.store(command.toEntity());

        PaymentTransactionEntity paymentTransactionEntity = paymentSupporter.makePaymentTransactionEntity(storePointPaymentOrderEntity);
        PaymentTransactionEntity storeEntity = paymentStore.store(paymentTransactionEntity);

        paymentProcessor.initPaymentOrder(storeEntity, command.getPaymentMethod());

        return InitPointCharge.from(storePointPaymentOrderEntity, storeEntity);
    }

    public PaymentInfo.PayPointCharge callPayPayment(PaymentCommand.PayPointCharge command) {
        // 페이먼트 트랜잭션에서 uuid 가져오기
        PaymentTransactionEntity paymentTransaction
            = paymentReader.getPaymentTransactionByPointPaymentOrder(command.getPaymentMethod(), command.getPointPaymentOrderId());

        // 결제 api 호출
        PaymentTransactionEntity payedPaymentTransactionEntity = paymentProcessor.pay(paymentTransaction, command.getPaymentMethod());

        PaymentTransactionEntity paymentTransactionEntity = paymentStore.store(payedPaymentTransactionEntity);

        PointPaymentOrderEntity pointPaymentOrderEntity = paymentReader.getPointPaymentOrderById(command.getPointPaymentOrderId());
        pointPaymentOrderEntity.setPaymentStatus(PaymentStatus.COMPLETED);
        
        paymentStore.store(pointPaymentOrderEntity);

        return paymentSupporter.makePaymentInfoFromTransactionEntity(paymentTransactionEntity, command.getPaymentMethod());
    }
}
