package com.zerobase.order.order.domain.repository;

import com.zerobase.order.order.domain.model.BookProductRelationEntity;
import com.zerobase.order.order.domain.model.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductStoreImpl implements ProductStore {

    private final ProductRepository productRepository;
    private final BookProductRelationRepository bookProductRelationRepository;

    @Override

    public ProductEntity store(ProductEntity entity) {
        return productRepository.save(entity);
    }

    @Override
    public BookProductRelationEntity store(BookProductRelationEntity entity) {
        return bookProductRelationRepository.save(entity);
    }
}
