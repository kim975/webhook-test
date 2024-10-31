package com.zerobase.user.point.domain.repository.payment;

import com.zerobase.user.point.domain.model.payment.TossPaymentTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TossPaymentTransactionRepository extends JpaRepository<TossPaymentTransactionEntity, Long> {

    TossPaymentTransactionEntity findByPointPaymentOrderId(Long pointPaymentOrderId);
}
