package com.zerobase.order.order.domain.repository;

import com.zerobase.order.order.domain.model.OrderTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTransactionRepository extends JpaRepository<OrderTransactionEntity, Long> {

}
