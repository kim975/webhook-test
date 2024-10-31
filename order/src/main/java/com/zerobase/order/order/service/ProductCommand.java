package com.zerobase.order.order.service;

import com.zerobase.order.order.domain.model.ProductEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ProductCommand {

    @Getter
    @Setter
    @Builder
    @ToString
    public static class RegisterProduct {

        private Long bookId;
        private String name;
        private int price;
        private int quantity;

        public ProductEntity toEntity() {
            return ProductEntity.builder()
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
    public static class ModifyProduct {

        private Long productId;
        private String name;
        private int price;
        private int quantity;

    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class MinusProductQuantity {

        private Long productId;
        private int reduceQuantity;
    }
}
