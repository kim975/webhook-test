package com.zerobase.order.api.point;

import com.zerobase.order.api.point.PointApiDto.PointResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class PointApi {

    private final RestTemplate restTemplate;

    //TODO 포인트변경 API 개발후 변경
    public PointApiDto.PointResponse changePoint(Long userId, int changePoint) {

//        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance()
//            .scheme("http")
//            .host("localhost")
//            .port(8080)
//            .path("/internal/api/v1/user")
//            .queryParam("query", query)
//            .queryParam("searchType", searchType.getSearchType());

        PointApiDto.PointResponse pointResponse = new PointResponse();
        pointResponse.setResult("SUCCESS");

        PointApiDto.PointDetail pointDetail = new PointApiDto.PointDetail();
        pointDetail.setUserId(String.valueOf(userId));
        pointDetail.setPoint(String.valueOf(10000 - changePoint));

        pointResponse.setData(pointDetail);

//        return restTemplate.getForObject(uriComponentsBuilder.build().toString(), PointApiDto.PointResponse.class);
        return pointResponse;
    }

}
