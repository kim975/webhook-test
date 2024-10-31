package com.zerobase.order.order.service;

import com.zerobase.order.api.point.PointApi;
import com.zerobase.order.order.domain.model.OrderEntity;
import com.zerobase.order.order.domain.model.OrderTransactionEntity;
import com.zerobase.order.order.domain.model.ProductEntity;
import com.zerobase.order.order.domain.repository.OrderReader;
import com.zerobase.order.order.domain.repository.OrderStore;
import com.zerobase.order.order.domain.repository.ProductReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductReader productReader;
    private final OrderReader orderReader;
    private final OrderStore orderStore;
    private final PointApi pointApi;

    @Transactional
    public OrderInfo.Main registerOrder(OrderCommand.RegisterOrder command) {

        //주문
        ProductEntity product = productReader.getProductByProductId(command.getProductId());
        OrderEntity order = orderStore.store(command.toEntity(product));

        //주문 transaction 저장
        OrderTransactionEntity orderTransactionEntity = order.toOrderTransactionEntity();
        orderStore.store(orderTransactionEntity);

        return OrderInfo.Main.from(order);

    }

}
