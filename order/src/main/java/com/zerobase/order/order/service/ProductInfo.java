package com.zerobase.order.order.service;

import com.zerobase.order.order.domain.model.ProductEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ProductInfo {

    @Getter
    @Setter
    @Builder
    @ToString
    public static class Main {

        private Long id;
        private String name;
        private int price;
        private int quantity;

        public static ProductInfo.Main from(ProductEntity product) {
            return ProductInfo.Main.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
        }
    }

}
