package com.zerobase.order.order.controller;

import com.zerobase.order.order.application.OrderFacadeDto;
import com.zerobase.order.order.service.OrderInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class OrderDto {

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderRequest {

        private Long productId;
        private String deliveryAddress;
        private String receiverName;
        private String receiverPhoneNumber;
        private int orderQuantity;

        public OrderFacadeDto.RegisterOrderRequest toDto(String userUuid) {
            return OrderFacadeDto.RegisterOrderRequest.builder()
                .productId(productId)
                .userUuid(userUuid)
                .deliveryAddress(deliveryAddress)
                .receiverName(receiverName)
                .receiverPhoneNumber(receiverPhoneNumber)
                .orderQuantity(orderQuantity)
                .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class RegisterOrderResponse {

        private String productName;
        private String deliveryAddress;
        private String receiverName;
        private String receiverPhoneNumber;
        private int orderPrice;
        private int orderQuantity;
        private String orderType;

        public static OrderDto.RegisterOrderResponse from(OrderInfo.Main dto) {
            return RegisterOrderResponse.builder()
                .productName(dto.getProduct().getName())
                .deliveryAddress(dto.getDeliveryAddress())
                .receiverName(dto.getReceiverName())
                .receiverPhoneNumber(dto.getReceiverPhoneNumber())
                .orderPrice(dto.getOrderPrice())
                .orderQuantity(dto.getOrderQuantity())
                .orderType(dto.getOrderType().toString())
                .build();
        }
    }
}
