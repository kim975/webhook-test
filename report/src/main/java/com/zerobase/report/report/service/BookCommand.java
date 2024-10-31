package com.zerobase.report.report.service;

import com.zerobase.report.report.domain.model.BookEntity;
import com.zerobase.report.report.service.BookInfo.BookApiDetail;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BookCommand {

    @Getter
    @Setter
    @ToString
    @Builder
    public static class RegisterBook {

        private String title;
        private String author;
        private String publisher;
        private String isbn;
        private String thumbnailImageUrl;
        private LocalDate publishedDate;

        public static BookCommand.RegisterBook fromBookInfo(BookApiDetail bookInfo) {
            return BookCommand.RegisterBook.builder()
                .title(bookInfo.getTitle())
                .author(bookInfo.getAuthor())
                .publisher(bookInfo.getPublisher())
                .isbn(bookInfo.getIsbn())
                .thumbnailImageUrl(bookInfo.getThumbnailImageUrl())
                .publishedDate(bookInfo.getPublishedDate())
                .build();
        }

        public BookEntity toEntity() {
            return BookEntity.builder()
                .title(title)
                .author(author)
                .publisher(publisher)
                .isbn(isbn)
                .thumbnailImageUrl(thumbnailImageUrl)
                .publishedDate(publishedDate)
                .build();
        }
    }


}
