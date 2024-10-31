package com.zerobase.order.order.domain.repository;

import com.zerobase.order.exception.BaseException;
import com.zerobase.order.exception.ProductErrorCode;
import com.zerobase.order.order.domain.model.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductReaderImpl implements ProductReader {

    private final ProductRepository productRepository;

    @Override
    public ProductEntity getProductByProductId(Long productId) {
        return productRepository.findById(productId)
            .orElseThrow(() -> new BaseException(ProductErrorCode.NOT_FOUND_PRODUCT));
    }
}
