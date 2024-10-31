package com.zerobase.report.report.service;

import com.zerobase.report.report.domain.model.BookEntity;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BookInfo {

    @Getter
    @Setter
    @ToString
    @Builder
    public static class Main {

        private Long id;
        private String title;
        private String author;
        private String publisher;
        private String isbn;
        private String thumbnailImageUrl;
        private LocalDate publishedDate;

        public static BookInfo.Main fromEntity(BookEntity book) {
            return BookInfo.Main.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .isbn(book.getIsbn())
                .thumbnailImageUrl(book.getThumbnailImageUrl())
                .publishedDate(book.getPublishedDate())
                .build();
        }
    }

    @Getter
    @Setter
    @ToString
    @Builder
    public static class BookApiMain {

        private int total;
        private int page;
        private int size;
        private List<BookApiDetail> bookApiDetailList;

    }

    @Getter
    @Setter
    @ToString
    @Builder
    public static class BookApiDetail {

        private String title;
        private String author;
        private String publisher;
        private String isbn;
        private String thumbnailImageUrl;
        private LocalDate publishedDate;
        private Integer price;
        private String description;

        public BookInfo.Main toMain() {
            return BookInfo.Main.builder()
                .title(title)
                .author(author)
                .isbn(isbn)
                .publishedDate(publishedDate)
                .publisher(publisher)
                .thumbnailImageUrl(thumbnailImageUrl)
                .build();
        }
    }

}
