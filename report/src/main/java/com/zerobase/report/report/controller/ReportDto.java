package com.zerobase.report.report.controller;

import com.zerobase.report.report.application.BookFacadeDto;
import com.zerobase.report.report.application.ReportFacadeDto;
import com.zerobase.report.report.application.ReportFacadeDto.BookDetail;
import com.zerobase.report.report.service.BookInfo;
import com.zerobase.report.report.service.ReportInfo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ReportDto {

    @Getter
    @Setter
    @Builder
    @ToString
    public static class GetReportResponse {

        private Long bookReportSeq;
        private LocalDateTime readDateTime;
        private String text;
        private Boolean reveal;
        private String userNickname;
        private ReportDto.Book book;
        private GetReportResponse.User user;

        public static GetReportResponse from(ReportFacadeDto.GetReportResponse report) {
            return GetReportResponse.builder()
                .book(Book.from(report.getBook()))
                .bookReportSeq(report.getBookReportSeq())
                .readDateTime(report.getReadDateTime())
                .text(report.getText())
                .reveal(report.getReveal())
                .user(User.from(report.getUser()))
                .build();
        }

        @Getter
        @Setter
        @Builder
        @ToString
        private static class User {

            private String nickname;

            public static User from(ReportFacadeDto.UserDetail user) {
                return User.builder()
                    .nickname(user.getNickname())
                    .build();
            }
        }
    }

    @Getter
    @Setter
    @ToString
    public static class CreateReportRequest {

        private Long bookId;
        private LocalDateTime readDatetime; //yyyy-MM-ddTHH:mm:ss
        private String text;
        private Boolean reveal;

        public BookFacadeDto.CreateReportRequest toFacadeDto(String userUuid) {
            return BookFacadeDto.CreateReportRequest.builder()
                .bookId(bookId)
                .userUuid(userUuid)
                .readDatetime(readDatetime)
                .text(text)
                .reveal(reveal)
                .build();
        }

    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class CreateReportResponse {

        private ReportDto.Book book;
        private Long bookReportSeq;
        private LocalDateTime readDateTime;
        private String text;
        private Boolean reveal;

        public static ReportDto.CreateReportResponse from(ReportInfo.Main report) {
            return CreateReportResponse.builder()
                .book(Book.from(report.getBook()))
                .bookReportSeq(report.getBookReportSeq())
                .readDateTime(report.getReadDateTime())
                .text(report.getText())
                .reveal(report.getReveal())
                .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @ToString
    private static class Book {

        private Long id;
        private String title;
        private String author;
        private String publisher;
        private String isbn;
        private String thumbnailImageUrl;
        private LocalDate publishedDate;

        public static ReportDto.Book from(BookInfo.Main bookInfo) {
            return ReportDto.Book.builder()
                .id(bookInfo.getId())
                .title(bookInfo.getTitle())
                .author(bookInfo.getAuthor())
                .publisher(bookInfo.getPublisher())
                .isbn(bookInfo.getIsbn())
                .thumbnailImageUrl(bookInfo.getThumbnailImageUrl())
                .publishedDate(bookInfo.getPublishedDate())
                .build();
        }

        public static ReportDto.Book from(BookDetail bookInfo) {
            return ReportDto.Book.builder()
                .id(bookInfo.getId())
                .title(bookInfo.getTitle())
                .author(bookInfo.getAuthor())
                .publisher(bookInfo.getPublisher())
                .isbn(bookInfo.getIsbn())
                .thumbnailImageUrl(bookInfo.getThumbnailImageUrl())
                .publishedDate(bookInfo.getPublishedDate())
                .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class ModifyReportRequest {

        private Long bookId;
        private LocalDateTime readDatetime; //yyyy-MM-ddTHH:mm:ss
        private String text;
        private Boolean reveal;

        public ReportFacadeDto.ModifyReportRequest toDto(String userUuid, Long reportSeq) {
            return ReportFacadeDto.ModifyReportRequest.builder()
                .bookId(bookId)
                .readDatetime(readDatetime)
                .text(text)
                .reveal(reveal)
                .reportSeq(reportSeq)
                .userUuid(userUuid)
                .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class ModifyReportResponse {

        private ReportDto.Book book;
        private Long bookReportSeq;
        private LocalDateTime readDateTime;
        private String text;
        private Boolean reveal;

        public static ModifyReportResponse from(ReportInfo.Main dto) {
            return ModifyReportResponse.builder()
                .book(Book.from(dto.getBook()))
                .bookReportSeq(dto.getBookReportSeq())
                .readDateTime(dto.getReadDateTime())
                .text(dto.getText())
                .reveal(dto.getReveal())
                .build();
        }
    }

}
