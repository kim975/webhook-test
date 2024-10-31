package com.zerobase.user.point.domain.repository.payment;

import com.zerobase.user.point.domain.model.PaymentMethod;
import com.zerobase.user.point.domain.model.payment.PaymentTransactionEntity;
import com.zerobase.user.point.domain.model.payment.PointPaymentOrderEntity;

public interface PaymentReader {

    PaymentTransactionEntity getPaymentTransactionByPointPaymentOrder(PaymentMethod paymentMethod, Long pointPaymentOrderId);

    PointPaymentOrderEntity getPointPaymentOrderById(Long pointPaymentOrderId);
}
