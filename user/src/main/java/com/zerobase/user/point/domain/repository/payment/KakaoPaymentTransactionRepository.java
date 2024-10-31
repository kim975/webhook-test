package com.zerobase.user.point.domain.repository.payment;

import com.zerobase.user.point.domain.model.payment.KakaoPaymentTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KakaoPaymentTransactionRepository extends JpaRepository<KakaoPaymentTransactionEntity, Long> {

    KakaoPaymentTransactionEntity findByPointPaymentOrderId(Long pointPaymentOrderId);
}
