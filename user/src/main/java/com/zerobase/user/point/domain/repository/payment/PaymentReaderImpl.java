package com.zerobase.user.point.domain.repository.payment;

import com.zerobase.user.exception.BaseException;
import com.zerobase.user.exception.PaymentErrorCode;
import com.zerobase.user.point.domain.model.PaymentMethod;
import com.zerobase.user.point.domain.model.payment.PaymentTransactionEntity;
import com.zerobase.user.point.domain.model.payment.PointPaymentOrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentReaderImpl implements PaymentReader {

    private final PointPaymentOrderRepository pointPaymentOrderRepository;
    private final KakaoPaymentTransactionRepository kakaoPaymentTransactionRepository;
    private final TossPaymentTransactionRepository tossPaymentTransactionRepository;

    @Override
    public PaymentTransactionEntity getPaymentTransactionByPointPaymentOrder(PaymentMethod paymentMethod, Long pointPaymentOrderId) {

        if (PaymentMethod.KAKAO == paymentMethod) {
            return kakaoPaymentTransactionRepository.findByPointPaymentOrderId(pointPaymentOrderId);

        } else if (PaymentMethod.TOSS == paymentMethod) {
            return tossPaymentTransactionRepository.findByPointPaymentOrderId(pointPaymentOrderId);

        } else {
            throw new BaseException(PaymentErrorCode.NOT_FOUND_PG);
        }
    }

    @Override
    public PointPaymentOrderEntity getPointPaymentOrderById(Long pointPaymentOrderId) {
        return pointPaymentOrderRepository.findById(pointPaymentOrderId)
            .orElseThrow(() -> new BaseException(PaymentErrorCode.NOT_FOUND_PG));
    }
}
