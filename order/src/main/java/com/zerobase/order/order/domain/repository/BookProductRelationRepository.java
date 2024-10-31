package com.zerobase.order.order.domain.repository;

import com.zerobase.order.order.domain.model.BookProductRelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookProductRelationRepository extends JpaRepository<BookProductRelationEntity, Long> {

}
