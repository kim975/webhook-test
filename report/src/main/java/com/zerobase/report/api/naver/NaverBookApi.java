package com.zerobase.report.api.naver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.report.api.BookApi;
import com.zerobase.report.api.BookSearchForm;
import com.zerobase.report.exception.ApiErrorCode;
import com.zerobase.report.exception.ApiException;
import com.zerobase.report.report.service.BookInfo.BookApiDetail;
import com.zerobase.report.report.service.BookInfo.BookApiMain;
import com.zerobase.report.util.PageUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class NaverBookApi implements BookApi {

    @Value(value = "${naverClientId}")
    private String naverClientId;

    @Value(value = "${naverClientSecret}")
    private String naverClientSecret;

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    private final String SEARCH_LIST = "book.json";
    private final String SEARCH_DETAIL = "book_adv.json";

    @Override
    public List<BookApiDetail> findBookList(BookSearchForm bookSearchForm) {
        return searchBook(bookSearchForm, SEARCH_LIST).getBookApiDetailList();
    }

    @Override
    public Page<BookApiDetail> findBookListWithPage(BookSearchForm bookSearchForm) {
        BookApiMain bookApiMain = searchBook(bookSearchForm, SEARCH_LIST);
        return PageUtil.makePage(bookApiMain.getBookApiDetailList(), bookApiMain.getPage(), bookApiMain.getSize(), bookApiMain.getTotal());
    }

    @Override
    public BookApiDetail findBookDetail(BookSearchForm bookSearchForm) {

        List<BookApiDetail> bookApiDetailList = searchBook(bookSearchForm, SEARCH_DETAIL).getBookApiDetailList();

        if (bookApiDetailList.isEmpty()) {
            throw new ApiException(ApiErrorCode.NOT_FOUND_BOOK);
        }

        if (bookApiDetailList.size() >= 2) {
            throw new ApiException(ApiErrorCode.IS_NOT_UNIQUE);
        }

        return bookApiDetailList.get(0);
    }

    private BookApiMain searchBook(BookSearchForm bookSearchForm, String searchTypeUrl) {

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance()
            .scheme("https")
            .host("openapi.naver.com")
            .path("/v1/search/" + searchTypeUrl);

        bookSearchForm.makeQueryParam(uriComponentsBuilder).build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);

        return restTemplate.exchange(
                uriComponentsBuilder.build().toString(),
                HttpMethod.GET,
                new HttpEntity<>(headers),
                NaverBookResponse.class
            )
            .getBody()
            .toBookApiMain();
    }

}
