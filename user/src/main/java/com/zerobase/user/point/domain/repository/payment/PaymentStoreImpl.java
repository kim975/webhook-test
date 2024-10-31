package com.zerobase.user.point.domain.repository.payment;

import com.zerobase.user.exception.BaseException;
import com.zerobase.user.exception.PaymentErrorCode;
import com.zerobase.user.point.domain.model.payment.PointPaymentOrderEntity;
import com.zerobase.user.point.domain.model.payment.KakaoPaymentTransactionEntity;
import com.zerobase.user.point.domain.model.payment.PaymentTransactionEntity;
import com.zerobase.user.point.domain.model.payment.TossPaymentTransactionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentStoreImpl implements PaymentStore {

    private final KakaoPaymentTransactionRepository kakaoPaymentTransactionRepository;
    private final TossPaymentTransactionRepository tossPaymentTransactionRepository;
    private final PointPaymentOrderRepository pointPaymentOrdersRepository;

    @Override
    public PaymentTransactionEntity store(PaymentTransactionEntity paymentTransactionEntity) {

        if (paymentTransactionEntity instanceof KakaoPaymentTransactionEntity) {
            return kakaoPaymentTransactionRepository.save((KakaoPaymentTransactionEntity) paymentTransactionEntity);

        } else if (paymentTransactionEntity instanceof TossPaymentTransactionEntity) {
            return tossPaymentTransactionRepository.save((TossPaymentTransactionEntity) paymentTransactionEntity);

        } else {
            throw new BaseException(PaymentErrorCode.NOT_FOUND_PG);
        }
    }

    @Override
    public PointPaymentOrderEntity store(PointPaymentOrderEntity pointPaymentOrder) {
        return pointPaymentOrdersRepository.save(pointPaymentOrder);
    }

}
