package com.zerobase.report.report.domain.repository;

import com.zerobase.report.report.domain.model.BookReportEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReportRepository extends JpaRepository<BookReportEntity, Long> {

    Optional<BookReportEntity> findTopByUserIdOrderByBookReportSeqDesc(Long userId);

    Page<BookReportEntity> findByUserId(Long userId, Pageable pageable);

    Optional<BookReportEntity> findByUserIdAndBookReportSeq(Long userId, Long bookReportSeq);

    Optional<BookReportEntity> findByUserIdAndBookReportSeqAndReveal(Long userId, Long bookReportSeq, boolean reveal);

    Page<BookReportEntity> findByUserIdInAndReveal(List<Long> userIds, boolean reveal, Pageable pageable);
}
