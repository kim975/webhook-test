package com.zerobase.user.point.domain.repository.payment;

import com.zerobase.user.point.domain.model.payment.PointPaymentOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointPaymentOrderRepository extends JpaRepository<PointPaymentOrderEntity, Long> {

}
