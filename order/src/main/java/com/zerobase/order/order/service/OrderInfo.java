package com.zerobase.order.order.service;

import com.zerobase.order.order.domain.model.OrderEntity;
import com.zerobase.order.order.domain.model.OrderType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class OrderInfo {

    @Getter
    @Setter
    @Builder
    @ToString
    public static class Main {

        private ProductInfo.Main product;
        private Long userId;
        private String orderToken;
        private String deliveryAddress;
        private String receiverName;
        private String receiverPhoneNumber;
        private int orderPrice;
        private int orderQuantity;
        private OrderType orderType;

        public static OrderInfo.Main from(OrderEntity order) {
            return OrderInfo.Main.builder()
                .userId(order.getUserId())
                .orderToken(order.getOrderToken())
                .deliveryAddress(order.getDeliveryAddress())
                .receiverName(order.getReceiverName())
                .receiverPhoneNumber(order.getReceiverPhoneNumber())
                .orderPrice(order.getOrderPrice())
                .orderQuantity(order.getOrderQuantity())
                .orderType(order.getOrderType())
                .product(ProductInfo.Main.from(order.getProduct()))
                .build();
        }

    }

}
