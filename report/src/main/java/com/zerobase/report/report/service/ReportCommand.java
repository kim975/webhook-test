package com.zerobase.report.report.service;

import com.zerobase.report.aop.ReportSeqLockInterface;
import com.zerobase.report.report.domain.model.BookEntity;
import com.zerobase.report.report.domain.model.BookReportEntity;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ReportCommand {

    @Getter
    @Setter
    @Builder
    @ToString
    public static class CreateReport implements ReportSeqLockInterface {

        private Long bookId;
        private Long userId;
        private LocalDateTime readDatetime;
        private String text;
        private Boolean reveal;

        public BookReportEntity toEntity(BookEntity bookEntity) {
            return BookReportEntity.builder()
                .bookEntity(bookEntity)
                .userId(userId)
                .text(text)
                .reveal(reveal)
                .readDateTime(readDatetime)
                .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class ModifyReport {
        private Long bookId;
        private Long reportSeq;
        private Long userId;
        private LocalDateTime readDatetime;
        private String text;
        private Boolean reveal;
    }
}
