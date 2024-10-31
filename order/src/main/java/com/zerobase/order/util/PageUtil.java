package com.zerobase.order.util;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

public class PageUtil {

    public static <T extends List> Page makePage(T data, int page, int size, int totalCount) {
        return new PageImpl(data, PageRequest.of(page, size), totalCount);
    }

}
