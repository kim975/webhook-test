package com.zerobase.order.order.domain.repository;

import com.zerobase.order.order.domain.model.OrderEntity;
import com.zerobase.order.order.domain.model.OrderTransactionEntity;

public interface OrderStore {

    OrderEntity store(OrderEntity entity);

    OrderTransactionEntity store(OrderTransactionEntity orderTransactionEntity);
}
