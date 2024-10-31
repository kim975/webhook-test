package com.zerobase.user.point.domain.repository;

import com.zerobase.user.point.domain.model.PointHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointHistoryRepository extends JpaRepository<PointHistoryEntity, Long> {
}
