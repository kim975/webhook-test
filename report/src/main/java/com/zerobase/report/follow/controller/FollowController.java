package com.zerobase.report.follow.controller;

import com.zerobase.report.common.response.CommonResponse;
import com.zerobase.report.follow.application.FollowFacade;
import com.zerobase.report.follow.application.FollowFacadeDto.MainRequest;
import com.zerobase.report.follow.controller.FollowDto.FollowUserInfoResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowFacade followFacade;

    @PostMapping("/follow")
    @PreAuthorize("hasRole('USER')")
    public CommonResponse<Void> registerFollow(
        Authentication authentication,
        @RequestBody FollowDto.RegisterFollowRequest request
    ) {
        followFacade.registerFollow(
            MainRequest.builder()
                .userUuid(authentication.getName())
                .followUserNickName(request.getFollowUserNickName())
                .build()
        );

        return CommonResponse.success();
    }

    @DeleteMapping("/follow")
    @PreAuthorize("hasRole('USER')")
    public CommonResponse<Void> unfollow(
        Authentication authentication,
        @RequestBody FollowDto.UnfollowRequest request
    ) {
        followFacade.unfollow(
            MainRequest.builder()
                .userUuid(authentication.getName())
                .followUserNickName(request.getFollowUserNickName())
                .build()
        );

        return CommonResponse.success();
    }

    @GetMapping("/follow")
    @PreAuthorize("hasRole('USER')")
    public CommonResponse<List<FollowDto.FollowUserInfoResponse>> getFollow(
        Authentication authentication
    ) {

        return CommonResponse.success(
            followFacade.getFollowUserInfo(authentication.getName())
                .stream()
                .map(FollowUserInfoResponse::fromDto)
                .toList()
        );
    }

    @GetMapping("/follower")
    @PreAuthorize("hasRole('USER')")
    public CommonResponse<List<FollowDto.FollowUserInfoResponse>> getFollower(
        Authentication authentication
    ) {
        return CommonResponse.success(
            followFacade.getFollowerUserInfo(authentication.getName())
                .stream()
                .map(FollowUserInfoResponse::fromDto)
                .toList()
        );
    }
}
