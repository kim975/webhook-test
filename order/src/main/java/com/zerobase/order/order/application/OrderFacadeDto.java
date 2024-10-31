package com.zerobase.order.order.application;

import com.zerobase.order.order.service.OrderCommand;
import com.zerobase.order.util.TokenGenerator;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class OrderFacadeDto {

    @Getter
    @Setter
    @Builder
    @ToString
    public static class RegisterOrderRequest {

        private Long productId;
        private String userUuid;
        private String deliveryAddress;
        private String receiverName;
        private String receiverPhoneNumber;
        private int orderQuantity;

        public OrderCommand.RegisterOrder toCommand(Long userId) {
            return OrderCommand.RegisterOrder.builder()
                .productId(productId)
                .userId(userId)
                .orderToken(TokenGenerator.getToken())
                .deliveryAddress(deliveryAddress)
                .receiverName(receiverName)
                .receiverPhoneNumber(receiverPhoneNumber)
                .orderQuantity(orderQuantity)
                .build();
        }

    }
}
