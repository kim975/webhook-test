package com.zerobase.user.user.controller;

import com.zerobase.user.common.response.CommonResponse;
import com.zerobase.user.user.application.UserFacade;
import com.zerobase.user.user.domain.model.UserSearchType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/api/v1")
public class UserInternalApiController {

    private final UserFacade userFacade;

    @GetMapping("/user")
    public CommonResponse<UserInternalApiDto.UserDetailResponse> getUser(
        @RequestParam String query,
        @RequestParam String searchType
    ) {
        return CommonResponse.success(
            UserInternalApiDto.UserDetailResponse.from(userFacade.getUserDetailByQuery(query, UserSearchType.find(searchType)))
        );
    }

    @GetMapping("/users")
    public CommonResponse<List<UserInternalApiDto.UserDetailResponse>> getUsers(
        @RequestParam List<String> query,
        @RequestParam String searchType
    ) {
        return CommonResponse.success(
            userFacade.getAllUserDetailByQuery(query, UserSearchType.find(searchType))
                .stream()
                .map(UserInternalApiDto.UserDetailResponse::from)
                .toList()
        );
    }

}
