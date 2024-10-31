package com.zerobase.user.point.service.payment;

import com.zerobase.user.point.domain.model.PaymentMethod;
import com.zerobase.user.point.domain.model.payment.PaymentTransactionEntity;

public interface PaymentApiCaller {

    boolean isSupport(PaymentMethod paymentMethod);

    PaymentTransactionEntity pay(PaymentTransactionEntity entity);

    void initPaymentOrder(PaymentTransactionEntity entity);
}
