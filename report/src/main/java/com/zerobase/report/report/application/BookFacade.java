package com.zerobase.report.report.application;

import com.zerobase.report.api.BookApi;
import com.zerobase.report.api.BookSearchForm;
import com.zerobase.report.api.user.UserApi;
import com.zerobase.report.api.user.UserApiDto.UserResponse;
import com.zerobase.report.api.user.UserSearchType;
import com.zerobase.report.report.application.BookFacadeDto.BookResponse;
import com.zerobase.report.report.application.BookFacadeDto.BookResponse.DataType;
import com.zerobase.report.report.service.BookCommand.RegisterBook;
import com.zerobase.report.report.service.BookInfo;
import com.zerobase.report.report.service.BookInfo.BookApiDetail;
import com.zerobase.report.report.service.BookService;
import com.zerobase.report.report.service.ReportInfo;
import com.zerobase.report.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookFacade {

    private final UserApi userApi;
    private final BookService bookService;
    private final ReportService reportService;
    private final BookApi bookApi;

    public Page<BookResponse> findBookListWithPage(String bookTitle, Pageable pageable) {

        Page<BookInfo.Main> bookListDb = bookService.getBookListWithPaging(bookTitle, pageable);

        if (!bookListDb.isEmpty()) {
            return bookListDb.map(book -> BookFacadeDto.BookResponse.fromBookInfo(book, DataType.DB));
        }

        return findBookListOnlyApi(bookTitle, pageable);
    }

    public Page<BookFacadeDto.BookResponse> findBookListOnlyApi(String bookTitle, Pageable pageable) {

        return bookApi.findBookListWithPage(
                BookSearchForm.builder()
                    .query(bookTitle)
                    .start(pageable.getPageNumber())
                    .display(pageable.getPageSize())
                    .build()
            )
            .map(book -> BookFacadeDto.BookResponse.fromBookInfo(book, DataType.API));
    }

    public BookInfo.Main registerBook(String isbn) {
        if (!bookService.isExistsBook(isbn)) {

            BookApiDetail bookApiInfo = bookApi.findBookDetail(
                BookSearchForm.builder()
                    .dIsbn(isbn)
                    .build()
            );

            return bookService.registerBook(RegisterBook.fromBookInfo(bookApiInfo));

            //TODO order 도메인에 상품 정보 등록하기
        } else {
            return bookService.getBookByIsbn(isbn);
        }

    }

    public ReportInfo.Main createReport(BookFacadeDto.CreateReportRequest dto) {
        UserResponse user = userApi.getUser(dto.getUserUuid(), UserSearchType.USER_UUID);
        return reportService.createReport(dto.toCommand(user.getData().getId()));
    }

}
