package com.zerobase.user.point.service.payment;

import com.zerobase.user.point.domain.model.PaymentMethod;
import com.zerobase.user.point.domain.model.payment.PointPaymentOrderEntity;
import com.zerobase.user.point.domain.model.payment.PaymentTransactionEntity;
import com.zerobase.user.point.service.PaymentInfo;

public interface Payment {

    boolean isSupport(PaymentMethod paymentMethod);

    PaymentTransactionEntity makeTransactionEntity(PointPaymentOrderEntity pointPaymentOrder);

    PaymentInfo.PayPointCharge makePaymentInfoFromTransactionEntity(PaymentTransactionEntity transaction);

}
