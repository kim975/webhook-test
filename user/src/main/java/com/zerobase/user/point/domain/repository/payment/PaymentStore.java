package com.zerobase.user.point.domain.repository.payment;

import com.zerobase.user.point.domain.model.payment.PointPaymentOrderEntity;
import com.zerobase.user.point.domain.model.payment.PaymentTransactionEntity;

public interface PaymentStore {

    PaymentTransactionEntity store(PaymentTransactionEntity paymentTransactionEntity);

    PointPaymentOrderEntity store(PointPaymentOrderEntity pointPaymentOrder);

}
