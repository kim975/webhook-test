package com.zerobase.user.point.service.payment;

import com.zerobase.user.exception.BaseException;
import com.zerobase.user.exception.PaymentErrorCode;
import com.zerobase.user.point.domain.model.PaymentMethod;
import com.zerobase.user.point.domain.model.payment.PointPaymentOrderEntity;
import com.zerobase.user.point.domain.model.payment.PaymentTransactionEntity;
import com.zerobase.user.point.service.PaymentInfo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentSupporter {

    private final List<Payment> paymentList;

    public PaymentTransactionEntity makePaymentTransactionEntity(PointPaymentOrderEntity pointPaymentOrder) {
        Payment payment = routingPayment(pointPaymentOrder.getPaymentMethod());
        return payment.makeTransactionEntity(pointPaymentOrder);
    }

    public PaymentInfo.PayPointCharge makePaymentInfoFromTransactionEntity(PaymentTransactionEntity paymentTransaction, PaymentMethod paymentMethod) {
        Payment payment = routingPayment(paymentMethod);
        return payment.makePaymentInfoFromTransactionEntity(paymentTransaction);
    }

    private Payment routingPayment(PaymentMethod paymentMethod) {
        return paymentList.stream()
            .filter(payment -> payment.isSupport(paymentMethod))
            .findFirst()
            .orElseThrow(() -> new BaseException(PaymentErrorCode.NOT_FOUND_PG));
    }
}
