package com.zerobase.report.report.service;

import com.zerobase.report.aop.ReportSeqLock;
import com.zerobase.report.report.domain.model.BookEntity;
import com.zerobase.report.report.domain.model.BookReportEntity;
import com.zerobase.report.report.domain.repository.BookReader;
import com.zerobase.report.report.domain.repository.ReportReader;
import com.zerobase.report.report.domain.repository.ReportStore;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportReader reportReader;
    private final ReportStore reportStore;
    private final BookReader bookReader;

    @ReportSeqLock
    public ReportInfo.Main createReport(ReportCommand.CreateReport command) {
        BookEntity book = bookReader.getBookById(command.getBookId());
        Long reportSeq = reportReader.getTopSeqByUserId(command.getUserId());

        BookReportEntity report = command.toEntity(book);
        report.setBookReportSeq(reportSeq + 1);

        return ReportInfo.Main.fromEntity(reportStore.store(report));
    }

    public ReportInfo.Main getMyReport(Long userId, Long reportSeq) {
        return ReportInfo.Main.fromEntity(reportReader.getMyReport(userId, reportSeq));
    }

    public Page<ReportInfo.Main> getMyReports(Long userId, Pageable pageable) {
        return reportReader.getMyReports(userId, pageable).map(ReportInfo.Main::fromEntity);
    }

    public ReportInfo.Main getReport(Long userId, Long reportSeq) {
        return ReportInfo.Main.fromEntity(reportReader.getReport(userId, reportSeq));
    }

    public Page<ReportInfo.Main> getReports(List<Long> userIds, Pageable pageable) {
        return reportReader.getReports(userIds, pageable).map(ReportInfo.Main::fromEntity);
    }

    public ReportInfo.Main modifyReport(ReportCommand.ModifyReport command) {
        BookEntity book = bookReader.getBookById(command.getBookId());
        BookReportEntity myReport = reportReader.getMyReport(command.getUserId(), command.getReportSeq());

        myReport.setText(command.getText());
        myReport.setReveal(command.getReveal());
        myReport.setBookEntity(book);
        myReport.setReadDateTime(command.getReadDatetime());

        return ReportInfo.Main.fromEntity(reportStore.store(myReport));
    }

    public void deleteReport(Long userId, Long reportSeq) {
        reportStore.delete(userId, reportSeq);
    }
}
