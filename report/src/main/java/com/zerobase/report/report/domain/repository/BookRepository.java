package com.zerobase.report.report.domain.repository;

import com.zerobase.report.report.domain.model.BookEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findAllByTitleContainingIgnoreCase(String title);

    Page<BookEntity> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);

    boolean existsByIsbn(String isbn);

    Optional<BookEntity> findByIsbn(String isbn);

    Page<BookEntity> findAll(Pageable pageable);

}
