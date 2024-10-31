package com.zerobase.report.report.domain.repository;

import com.zerobase.report.exception.BaseException;
import com.zerobase.report.exception.BookErrorCode;
import com.zerobase.report.exception.ReportErrorCode;
import com.zerobase.report.report.domain.model.BookEntity;
import com.zerobase.report.report.domain.model.BookReportEntity;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookReaderImpl implements BookReader, ReportReader {

    private final BookRepository bookRepository;
    private final BookReportRepository bookReportRepository;

    @Override
    public List<BookEntity> getBookListByTitle(String title) {
        return bookRepository.findAllByTitleContainingIgnoreCase(title);
    }

    @Override
    public Page<BookEntity> getBookListByTitleWithPage(String title, Pageable pageable) {
        return bookRepository.findAllByTitleContainingIgnoreCase(title, pageable);
    }

    @Override
    public boolean isExistsBookByIsbn(String isbn) {
        return bookRepository.existsByIsbn(isbn);
    }

    @Override
    public BookEntity getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new BaseException(BookErrorCode.NOT_FOUND_BOOK));
    }

    @Override
    public BookEntity getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new BaseException(BookErrorCode.NOT_FOUND_BOOK));
    }

    @Override
    public Long getTopSeqByUserId(Long userId) {

        Optional<BookReportEntity> report = bookReportRepository.findTopByUserIdOrderByBookReportSeqDesc(userId);

        if (report.isEmpty()) {
            return 0L;
        }

        return report.get().getBookReportSeq();
    }

    @Override
    public BookReportEntity getMyReport(Long userId, Long reportSeq) {
        return bookReportRepository.findByUserIdAndBookReportSeq(userId, reportSeq)
            .orElseThrow(() -> new BaseException(ReportErrorCode.NOT_FOUND_REPORT));
    }

    @Override
    public Page<BookReportEntity> getMyReports(Long userId, Pageable pageable) {
        return bookReportRepository.findByUserId(userId, pageable);
    }

    @Override
    public BookReportEntity getReport(Long userId, Long reportSeq) {
        return bookReportRepository.findByUserIdAndBookReportSeqAndReveal(userId, reportSeq, true)
            .orElseThrow(() -> new BaseException(ReportErrorCode.NOT_FOUND_REPORT));
    }

    @Override
    public Page<BookReportEntity> getReports(List<Long> userIds, Pageable pageable) {
        return bookReportRepository.findByUserIdInAndReveal(userIds, true, pageable);
    }

    @Override
    public Page<BookEntity> getAllBookWithPage(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public long countBook() {
        return bookRepository.count();
    }

}
