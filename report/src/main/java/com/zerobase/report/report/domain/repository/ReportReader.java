package com.zerobase.report.report.domain.repository;

import com.zerobase.report.report.domain.model.BookReportEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReportReader {

    Long getTopSeqByUserId(Long userId);

    BookReportEntity getMyReport(Long userId, Long reportSeq);

    Page<BookReportEntity> getMyReports(Long userId, Pageable pageable);

    BookReportEntity getReport(Long userId, Long reportSeq);

    Page<BookReportEntity> getReports(List<Long> userIds, Pageable pageable);

}
