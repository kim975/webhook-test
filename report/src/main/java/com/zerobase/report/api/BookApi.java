package com.zerobase.report.api;

import com.zerobase.report.report.service.BookInfo.BookApiDetail;
import java.util.List;
import org.springframework.data.domain.Page;

public interface BookApi {

    Page<BookApiDetail> findBookListWithPage(BookSearchForm bookSearchForm);

    List<BookApiDetail> findBookList(BookSearchForm bookSearchForm);

    BookApiDetail findBookDetail(BookSearchForm bookSearchForm);

}
