package com.zerobase.report.api.naver;

import com.zerobase.report.report.service.BookInfo.BookApiDetail;
import com.zerobase.report.report.service.BookInfo.BookApiMain;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NaverBookResponse {

    private int total;
    private int start;
    private int display;
    private List<NaverBookDetail> items;

    public BookApiMain toBookApiMain() {
        return BookApiMain.builder()
            .page(start)
            .size(display)
            .total(total)
            .bookApiDetailList(toBookApiDetailList())
            .build();
    }

    private List<BookApiDetail> toBookApiDetailList() {

        return items.stream()
            .map(NaverBookDetail::toBookApiDetail)
            .collect(Collectors.toList());
    }


}
