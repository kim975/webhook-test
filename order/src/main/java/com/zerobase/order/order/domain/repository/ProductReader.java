package com.zerobase.order.order.domain.repository;

import com.zerobase.order.order.domain.model.ProductEntity;

public interface ProductReader {

    ProductEntity getProductByProductId(Long productId);
}
