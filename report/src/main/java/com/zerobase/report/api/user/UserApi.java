package com.zerobase.report.api.user;

import com.zerobase.report.api.user.UserApiDto.UserListResponse;
import com.zerobase.report.api.user.UserApiDto.UserResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class UserApi {

    private final RestTemplate restTemplate;

    public UserResponse getUser(String query, UserSearchType searchType) {

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance()
            .scheme("http")
            .host("localhost")
            .port(8080)
            .path("/internal/api/v1/user")
            .queryParam("query", query)
            .queryParam("searchType", searchType.getSearchType());

        return restTemplate.getForObject(uriComponentsBuilder.build().toString(), UserResponse.class);
    }

    public UserListResponse getUsers(List<String> query, UserSearchType searchType) {

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance()
            .scheme("http")
            .host("localhost")
            .port(8080)
            .path("/internal/api/v1/users")
            .queryParam("query", query)
            .queryParam("searchType", searchType.getSearchType());

        return restTemplate.getForObject(uriComponentsBuilder.build().toString(), UserListResponse.class);
    }

    public UserListResponse getUsers(List<Long> query) {

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance()
            .scheme("http")
            .host("localhost")
            .port(8080)
            .path("/internal/api/v1/users")
            .queryParam("query", query)
            .queryParam("searchType", UserSearchType.USER_ID.getSearchType());

        return restTemplate.getForObject(uriComponentsBuilder.build().toString(), UserListResponse.class);
    }

}
