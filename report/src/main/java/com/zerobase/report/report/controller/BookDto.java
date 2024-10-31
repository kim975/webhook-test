package com.zerobase.report.report.controller;

import com.zerobase.report.report.application.BookFacadeDto;
import com.zerobase.report.report.service.BookInfo;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BookDto {

    @Getter
    @Setter
    @Builder
    @ToString
    public static class BookSearchResponse {

        private String title;
        private String author;
        private String publisher;
        private String isbn;
        private String thumbnailImageUrl;
        private LocalDate publishedDate;
        private String dataType;

        public static BookDto.BookSearchResponse fromBookInfo(BookFacadeDto.BookResponse bookInfo) {
            return BookSearchResponse.builder()
                .title(bookInfo.getTitle())
                .author(bookInfo.getAuthor())
                .publisher(bookInfo.getPublisher())
                .isbn(bookInfo.getIsbn())
                .thumbnailImageUrl(bookInfo.getThumbnailImageUrl())
                .publishedDate(bookInfo.getPublishedDate())
                .dataType(bookInfo.getDataType().toString())
                .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterBookRequest {

        private String isbn;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class RegisterBookResponse {

        private Long id;
        private String title;
        private String author;
        private String publisher;
        private String isbn;
        private String thumbnailImageUrl;
        private LocalDate publishedDate;

        public static BookDto.RegisterBookResponse from(BookInfo.Main bookInfo) {
            return RegisterBookResponse.builder()
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
}
