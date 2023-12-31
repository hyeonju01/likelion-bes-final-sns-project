package com.likelion.mutsasns.controller;

import com.likelion.mutsasns.domain.entity.Response;
import com.likelion.mutsasns.domain.dto.*;
import com.likelion.mutsasns.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    @ApiOperation(value = "회원가입", notes = "회원가입합니다.")
    public ResponseEntity<UserJoinResponse> join(@RequestBody UserJoinRequest userJoinRequest) {
        UserJoinResponse userJoinResponse = userService.join(userJoinRequest);
        return ResponseEntity.ok().body(userJoinResponse);
    }

    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "본인의 Id와 password를 입력하여 로그인합니다.")
    public ResponseEntity<Response<UserLoginResponse>> login(@RequestBody UserLoginRequest userLoginRequest) {
        String token = userService.login(userLoginRequest.getUserName(), userLoginRequest.getPassword());
        UserLoginResponse loginResponse = new UserLoginResponse(token);
        return ResponseEntity.ok().body(Response.success(loginResponse));
    }
}