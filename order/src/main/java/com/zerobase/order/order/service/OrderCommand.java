package com.zerobase.order.order.service;

import com.zerobase.order.order.domain.model.OrderEntity;
import com.zerobase.order.order.domain.model.OrderType;
import com.zerobase.order.order.domain.model.ProductEntity;
import com.zerobase.order.util.TokenGenerator;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class OrderCommand {

    @Getter
    @Setter
    @Builder
    @ToString
    public static class RegisterOrder {

        private Long productId;
        private Long userId;
        private String orderToken;
        private String deliveryAddress;
        private String receiverName;
        private String receiverPhoneNumber;
        private int orderQuantity;

        public OrderEntity toEntity(ProductEntity product) {
            return OrderEntity.builder()
                .product(product)
                .userId(userId)
                .orderToken(TokenGenerator.getToken())
                .deliveryAddress(deliveryAddress)
                .receiverName(receiverName)
                .receiverPhoneNumber(receiverPhoneNumber)
                .orderPrice(product.getPrice() * orderQuantity)
                .orderQuantity(orderQuantity)
                .orderType(OrderType.PURCHASE)
                .build();
        }

    }

}
