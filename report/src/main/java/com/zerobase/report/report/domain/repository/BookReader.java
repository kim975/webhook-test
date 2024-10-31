package com.zerobase.report.report.domain.repository;

import com.zerobase.report.report.domain.model.BookEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookReader {

    List<BookEntity> getBookListByTitle(String title);

    Page<BookEntity> getBookListByTitleWithPage(String title, Pageable pageable);

    boolean isExistsBookByIsbn(String isbn);

    BookEntity getBookByIsbn(String isbn);

    BookEntity getBookById(Long bookId);

    Page<BookEntity> getAllBookWithPage(Pageable pageable);

    long countBook();
}
