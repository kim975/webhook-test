package com.zerobase.report.report.application;

import com.zerobase.report.report.service.BookInfo;
import com.zerobase.report.report.service.BookInfo.BookApiDetail;
import com.zerobase.report.report.service.ReportCommand;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BookFacadeDto {

    @Getter
    @Setter
    @ToString
    @Builder
    public static class BookResponse {

        private String title;
        private String author;
        private String publisher;
        private String isbn;
        private String thumbnailImageUrl;
        private LocalDate publishedDate;
        private DataType dataType;

        public static BookFacadeDto.BookResponse fromBookInfo(BookInfo.Main book, DataType dataType) {
            return BookResponse.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .isbn(book.getIsbn())
                .thumbnailImageUrl(book.getThumbnailImageUrl())
                .publishedDate(book.getPublishedDate())
                .dataType(dataType)
                .build();
        }

        public static BookFacadeDto.BookResponse fromBookInfo(BookApiDetail book, DataType dataType) {
            return BookResponse.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .isbn(book.getIsbn())
                .thumbnailImageUrl(book.getThumbnailImageUrl())
                .publishedDate(book.getPublishedDate())
                .dataType(dataType)
                .build();
        }

        public enum DataType {
            API, DB
        }

    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class CreateReportRequest {

        private Long bookId;
        private String userUuid;
        private LocalDateTime readDatetime;
        private String text;
        private Boolean reveal;

        public ReportCommand.CreateReport toCommand(Long userId) {
            return ReportCommand.CreateReport.builder()
                .bookId(bookId)
                .userId(userId)
                .readDatetime(readDatetime)
                .text(text)
                .reveal(reveal)
                .build();
        }
    }

}
