package com.zerobase.report.report.service;

import static com.zerobase.report.report.service.BookInfo.*;

import com.zerobase.report.report.domain.repository.BookReader;
import com.zerobase.report.report.domain.repository.BookStore;
import com.zerobase.report.report.service.BookCommand.RegisterBook;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookReader bookReader;
    private final BookStore bookStore;

    public List<Main> getBookList(String title) {
        return bookReader.getBookListByTitle(title).stream()
            .map(Main::fromEntity)
            .collect(Collectors.toList());
    }

    public Page<Main> getBookListWithPaging(String title, Pageable pageable) {
        return bookReader.getBookListByTitleWithPage(title, pageable)
            .map(Main::fromEntity);
    }

    public Main getBookByIsbn(String isbn) {
        return Main.fromEntity(bookReader.getBookByIsbn(isbn));
    }

    public boolean isExistsBook(String isbn) {
        return bookReader.isExistsBookByIsbn(isbn);
    }

    public Main registerBook(RegisterBook registerBook) {
        return Main.fromEntity(bookStore.store(registerBook.toEntity()));
    }

}
