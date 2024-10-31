package com.zerobase.order.order.domain.repository;

import com.zerobase.order.order.domain.model.OrderEntity;
import com.zerobase.order.order.domain.model.OrderTransactionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderStoreImpl implements OrderStore {

    private final OrderRepository orderRepository;
    private final OrderTransactionRepository orderTransactionRepository;

    @Override
    public OrderEntity store(OrderEntity entity) {
        return orderRepository.save(entity);
    }

    @Override
    public OrderTransactionEntity store(OrderTransactionEntity orderTransactionEntity) {
        return orderTransactionRepository.save(orderTransactionEntity);
    }
}
