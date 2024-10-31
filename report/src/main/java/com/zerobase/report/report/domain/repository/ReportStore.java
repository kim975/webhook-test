package com.zerobase.report.report.domain.repository;

import com.zerobase.report.report.domain.model.BookReportEntity;

public interface ReportStore {

    BookReportEntity store(BookReportEntity report);

    void delete(Long userId, Long reportSeq);
}
