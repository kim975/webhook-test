package com.zerobase.report.report.controller;

import com.zerobase.report.common.response.CommonResponse;
import com.zerobase.report.report.application.BookFacade;
import com.zerobase.report.report.application.BookFacadeDto;
import com.zerobase.report.report.controller.BookDto.BookSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookFacade bookFacade;

    @GetMapping("/books")
    public CommonResponse<Page<BookSearchResponse>> findBook(
        @RequestParam String bookTitle,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "false") boolean onlyApi
    ) {

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<BookFacadeDto.BookResponse> bookPage;

        if (onlyApi) {
            bookPage = bookFacade.findBookListOnlyApi(bookTitle, pageRequest);
        } else {
            bookPage = bookFacade.findBookListWithPage(bookTitle, pageRequest);
        }

        return CommonResponse.success(
            bookPage.map(BookSearchResponse::fromBookInfo)
        );
    }

    @PostMapping("/book")
    public CommonResponse<BookDto.RegisterBookResponse> registerBook(
        @RequestBody BookDto.RegisterBookRequest request
    ) {

        return CommonResponse.success(
            BookDto.RegisterBookResponse.from(bookFacade.registerBook(request.getIsbn()))
        );
    }

}
