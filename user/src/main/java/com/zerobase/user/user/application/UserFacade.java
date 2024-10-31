package com.zerobase.user.user.application;

import com.zerobase.user.point.service.PointService;
import com.zerobase.user.user.domain.model.UserSearchType;
import com.zerobase.user.user.service.UserCommand;
import com.zerobase.user.user.service.UserInfo;
import com.zerobase.user.user.service.UserInfo.UserDetail;
import com.zerobase.user.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final PointService pointService;
    private final UserService userService;

    public void signUp(UserCommand.SignUpUser command) {
        UserDetail userDetail = userService.signUp(command);
        pointService.initPoint(userDetail.getId());
    }

    public UserInfo.SignInInfo signIn(UserCommand.SignInUser command) {
        return userService.signIn(command);
    }

    public UserInfo.SignInInfo getUserAuthenticByUserUuid(String userUuid) {
        return userService.getUserAuthenticByUserUuid(userUuid);
    }

    public UserInfo.UserDetail getUserDetailByUserUuid(String userUuid) {
        return userService.getUserDetailByUuid(userUuid);
    }

    public UserInfo.UserDetail modifyUser(UserCommand.ModifyUser command) {
        return userService.modifyUserInfo(command);
    }

    public UserInfo.UserDetail leaveUser(String userUuid) {
        return userService.leaveUser(userUuid);
    }

    public UserInfo.UserDetail getUserDetailByQuery(String query, UserSearchType searchType) {
        return userService.getUserDetailByQuery(query, searchType);
    }

    public List<UserInfo.UserDetail> getAllUserDetailByQuery(List<String> query, UserSearchType searchType) {
        return userService.getAllUserDetailByQuery(query, searchType);
    }
}
