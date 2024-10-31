package com.zerobase.order.order.domain.repository;

import com.zerobase.order.order.domain.model.BookProductRelationEntity;
import com.zerobase.order.order.domain.model.ProductEntity;

public interface ProductStore {

    ProductEntity store(ProductEntity entity);

    BookProductRelationEntity store(BookProductRelationEntity build);
}
