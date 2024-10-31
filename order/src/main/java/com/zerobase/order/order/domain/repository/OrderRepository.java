package com.zerobase.order.order.domain.repository;

import com.zerobase.order.order.domain.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
