package com.zerobase.report.report.service;

import com.zerobase.report.report.domain.model.BookReportEntity;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ReportInfo {

    @Getter
    @Setter
    @Builder
    @ToString
    public static class Main {

        private Long id;
        private BookInfo.Main book;
        private Long userId;
        private Long bookReportSeq;
        private LocalDateTime readDateTime;
        private String text;
        private Boolean reveal;

        public static ReportInfo.Main fromEntity(BookReportEntity report) {
            return Main.builder()
                .id(report.getId())
                .book(BookInfo.Main.fromEntity(report.getBookEntity()))
                .userId(report.getUserId())
                .bookReportSeq(report.getBookReportSeq())
                .readDateTime(report.getReadDateTime())
                .text(report.getText())
                .reveal(report.getReveal())
                .build();
        }

    }

}
