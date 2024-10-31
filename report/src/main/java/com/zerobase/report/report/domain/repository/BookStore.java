package com.zerobase.report.report.domain.repository;

import com.zerobase.report.report.domain.model.BookEntity;

public interface BookStore {

    BookEntity store(BookEntity entity);
}
