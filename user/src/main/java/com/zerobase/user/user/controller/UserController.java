package com.zerobase.user.user.controller;

import com.zerobase.user.user.application.UserFacade;
import com.zerobase.user.common.properties.JwtConstant;
import com.zerobase.user.common.response.CommonResponse;
import com.zerobase.user.security.TokenProvider;
import com.zerobase.user.user.service.UserInfo;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;
    private final TokenProvider tokenProvider;


    @PostMapping("/signup")
    public CommonResponse<Void> signUp(
        @RequestBody UserDto.SignUpRequest request
    ) {
        userFacade.signUp(request.toCommand());
        return CommonResponse.success();
    }

    @PostMapping("/signin")
    public CommonResponse<UserDto.SignInResponse> signIn(
        @RequestBody UserDto.SignInRequest request,
        HttpServletResponse response
    ) {

        UserInfo.SignInInfo userInfo = userFacade.signIn(request.toCommand());

        response.addHeader(JwtConstant.TOKEN_HEADER, JwtConstant.TOKEN_PREFIX + tokenProvider.generateToken(userInfo));

        return CommonResponse.success(
            UserDto.SignInResponse.from(userInfo)
        );
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public CommonResponse<UserDto.UserDetailResponse> getUserInfo(
        Authentication authentication
    ) {
        return CommonResponse.success(
            UserDto.UserDetailResponse.from(userFacade.getUserDetailByUserUuid(authentication.getName()))
        );
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/user")
    public CommonResponse<UserDto.ModifyUserResponse> modifyUser(
        Authentication authentication,
        @RequestBody UserDto.ModifyUserRequest request
    ) {
        return CommonResponse.success(
            UserDto.ModifyUserResponse.from(userFacade.modifyUser(request.toCommand(authentication.getName())))
        );
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/user")
    public CommonResponse<Void> leaveUser(
        Authentication authentication
    ) {
        userFacade.leaveUser(authentication.getName());
        return CommonResponse.success();
    }

    @GetMapping("/test-user")
    @PreAuthorize("hasRole('USER')")
    public CommonResponse<String> testUser() {
        return CommonResponse.success("유저테스트");
    }

    @GetMapping("/test-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResponse<String> testADMIN() {
        return CommonResponse.success("관리자 테스트");
    }

    @GetMapping("/test-any")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public CommonResponse<String> testUserAndAdmin() {
        return CommonResponse.success("ANY 테스트");
    }

}
