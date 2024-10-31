package com.zerobase.report.report.service;

import com.zerobase.report.api.BookApi;
import com.zerobase.report.api.BookSearchForm;
import com.zerobase.report.exception.ApiException;
import com.zerobase.report.report.domain.model.BookEntity;
import com.zerobase.report.report.domain.repository.BookReader;
import com.zerobase.report.report.domain.repository.BookStore;
import com.zerobase.report.report.service.BookInfo.BookApiDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookSchedule {

    private final BookReader bookReader;
    private final BookStore bookStore;
    private final BookApi bookApi;

    private final static int PAGE_SIZE = 1000;

    @Scheduled(cron = "0 29 1 * * *")
    @Transactional
    public void syncBookSchedule() {

        long totalCount = bookReader.countBook();
        long totalPages = (long) Math.ceil((double) totalCount / PAGE_SIZE);

        for (int i = 0; i < totalPages; i++) {

            Page<BookEntity> allBookWithPage = bookReader.getAllBookWithPage(PageRequest.of(i, PAGE_SIZE));

            for (BookEntity book : allBookWithPage.getContent()) {

                String isbn = book.getIsbn();
                BookApiDetail bookDetail;
                try {
                    bookDetail = bookApi.findBookDetail(BookSearchForm.builder()
                        .dIsbn(isbn)
                        .build());

                } catch (ApiException e) {
                    log.error("isbn=%s error=%s", isbn, e.getMessage());
                    continue;
                }

                book.setAuthor(bookDetail.getAuthor());
                book.setTitle(bookDetail.getTitle());
                book.setPublisher(bookDetail.getPublisher());
                book.setPublishedDate(bookDetail.getPublishedDate());
                book.setThumbnailImageUrl(bookDetail.getThumbnailImageUrl());

            }
        }

    }

}
