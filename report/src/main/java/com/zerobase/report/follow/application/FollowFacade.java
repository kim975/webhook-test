package com.zerobase.report.follow.application;

import com.zerobase.report.api.user.UserApi;
import com.zerobase.report.api.user.UserApiDto.UserDetail;
import com.zerobase.report.api.user.UserApiDto.UserResponse;
import com.zerobase.report.api.user.UserSearchType;
import com.zerobase.report.follow.application.FollowFacadeDto.FollowUserInfoResponse;
import com.zerobase.report.follow.application.FollowFacadeDto.MainRequest;
import com.zerobase.report.follow.service.FollowCommand.Main;
import com.zerobase.report.follow.service.FollowInfo;
import com.zerobase.report.follow.service.FollowService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowFacade {

    private final FollowService followService;
    private final UserApi userApi;

    public void registerFollow(MainRequest dto) {

        UserResponse user = userApi.getUser(dto.getUserUuid(), UserSearchType.USER_UUID);
        UserResponse followUser = userApi.getUser(dto.getFollowUserNickName(), UserSearchType.NICKNAME);

        followService.registerFollow(
            Main.builder()
                .userId(user.getData().getId())
                .followUserId(followUser.getData().getId())
                .build()
        );

    }

    public void unfollow(MainRequest dto) {
        UserResponse user = userApi.getUser(dto.getUserUuid(), UserSearchType.USER_UUID);
        UserResponse followUser = userApi.getUser(dto.getFollowUserNickName(), UserSearchType.NICKNAME);

        followService.unfollow(
            Main.builder()
                .userId(user.getData().getId())
                .followUserId(followUser.getData().getId())
                .build()
        );
    }

    public List<FollowFacadeDto.FollowUserInfoResponse> getFollowUserInfo(String userUuid) {

        UserResponse user = userApi.getUser(userUuid, UserSearchType.USER_UUID);

        List<FollowInfo> followList = followService.getFollowList(user.getData().getId());

        List<UserDetail> userList = userApi.getUsers(
            followList.stream()
                .map(FollowInfo::getFollowUserId)
                .toList()
        ).getData();

        return userList.stream()
            .map(FollowUserInfoResponse::from)
            .toList();

    }

    public List<FollowFacadeDto.FollowUserInfoResponse> getFollowerUserInfo(String userUuid) {

        UserResponse user = userApi.getUser(userUuid, UserSearchType.USER_UUID);

        List<FollowInfo> followList = followService.getFollowerList((user.getData().getId()));

        List<UserDetail> userList = userApi.getUsers(
            followList.stream()
                .map(FollowInfo::getFollowUserId)
                .toList()
        ).getData();

        return userList.stream()
            .map(FollowUserInfoResponse::from)
            .toList();
    }
}
