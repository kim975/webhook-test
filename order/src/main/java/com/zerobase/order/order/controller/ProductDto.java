package com.zerobase.order.order.controller;

import com.zerobase.order.order.service.ProductCommand;
import com.zerobase.order.order.service.ProductInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ProductDto {

    @Getter
    @Setter
    @ToString
    public static class RegisterProductRequest {

        private Long bookId;
        private String name;
        private int price;
        private int quantity;

        public ProductCommand.RegisterProduct toCommand() {
            return ProductCommand.RegisterProduct.builder()
                .bookId(bookId)
                .name(name)
                .price(price)
                .quantity(quantity)
                .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class ModifyProductRequest {

        private Long productId;
        private String name;
        private int price;
        private int quantity;

        public ProductCommand.ModifyProduct toCommand() {
            return ProductCommand.ModifyProduct.builder()
                .productId(productId)
                .name(name)
                .price(price)
                .quantity(quantity)
                .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class GetProductResponse {

        private Long productId;
        private String name;
        private int price;
        private int quantity;

        public static GetProductResponse from(ProductInfo.Main dto) {
            return GetProductResponse.builder()
                .productId(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .build();
        }
    }
}
