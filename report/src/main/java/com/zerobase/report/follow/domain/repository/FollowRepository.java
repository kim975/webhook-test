package com.zerobase.report.follow.domain.repository;

import com.zerobase.report.follow.domain.model.FollowEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<FollowEntity, Long> {

    Optional<FollowEntity> findByUserIdAndFollowUserId(Long userId, Long followUserId);

    List<FollowEntity> findAllByUserId(Long userId);

    List<FollowEntity> findAllByFollowUserId(Long userId);
}
