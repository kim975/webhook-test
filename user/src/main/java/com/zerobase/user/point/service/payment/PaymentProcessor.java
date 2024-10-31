package com.zerobase.user.point.service.payment;

import com.zerobase.user.exception.BaseException;
import com.zerobase.user.exception.PaymentErrorCode;
import com.zerobase.user.point.domain.model.PaymentMethod;
import com.zerobase.user.point.domain.model.payment.PaymentTransactionEntity;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentProcessor {

    private final List<PaymentApiCaller> paymentApiCallerList;

    public void initPaymentOrder(PaymentTransactionEntity entity, PaymentMethod paymentMethod) {
        PaymentApiCaller payApiCaller = routingApiCaller(paymentMethod);
        payApiCaller.initPaymentOrder(entity);
    }

    public PaymentTransactionEntity pay(PaymentTransactionEntity entity, PaymentMethod paymentMethod) {
        PaymentApiCaller payApiCaller = routingApiCaller(paymentMethod);
        return payApiCaller.pay(entity);
    }

    private PaymentApiCaller routingApiCaller(PaymentMethod paymentMethod) {
        return paymentApiCallerList.stream()
            .filter(paymentApiCaller -> paymentApiCaller.isSupport(paymentMethod))
            .findFirst()
            .orElseThrow(() -> new BaseException(PaymentErrorCode.NOT_FOUND_PG));
    }
}
