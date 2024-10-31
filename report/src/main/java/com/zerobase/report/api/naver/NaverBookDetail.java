package com.zerobase.report.api.naver;

import com.zerobase.report.report.service.BookInfo.BookApiDetail;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NaverBookDetail {

    private String title;
    private String link;
    private String image;
    private String author;
    private String discount;
    private String publisher;
    private String pubdate;
    private String isbn;
    private String description;

    public BookApiDetail toBookApiDetail() {

        int year = Integer.parseInt(pubdate.substring(0, 4));
        int month = Integer.parseInt(pubdate.substring(4, 6));
        int day = Integer.parseInt(pubdate.substring(6, 8));

        return BookApiDetail.builder()
            .title(title)
            .author(author)
            .publisher(publisher)
            .publishedDate(LocalDate.of(year, month, day))
            .isbn(isbn)
            .thumbnailImageUrl(image)
            .price(Integer.parseInt(discount))
            .description(description)
            .build();
    }

}
