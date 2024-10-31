package com.zerobase.order.order.domain.repository;

import com.zerobase.order.order.domain.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
