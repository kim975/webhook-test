package com.zerobase.report.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
@ToString
@Builder
public class BookSearchForm {

    private String query;
    @Builder.Default
    private int display = 100; //max 100
    @Builder.Default
    private int start = 0; //max 100 // 스프링 Pageable 에서 시작은 0 쿼리 파라미터 만드시 +1 하도록 처리
    @Builder.Default
    private String sort = "sim"; // sim/date
    private String dTitle;
    private String dIsbn;

    public UriComponentsBuilder makeQueryParam(UriComponentsBuilder uriComponentsBuilder) {

        if (query != null) {
            uriComponentsBuilder.queryParam("query", query);
        }

        if (dTitle != null) {
            uriComponentsBuilder.queryParam("d_titl", dTitle);
        }

        if (dIsbn != null) {
            uriComponentsBuilder.queryParam("d_isbn", dIsbn);
        }

        uriComponentsBuilder
            .queryParam("display", display)
            .queryParam("start", start + 1)
            .queryParam("sort", sort);

        return uriComponentsBuilder;
    }

}
