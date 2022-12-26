package com.likelion.mutsasns.controller;

import com.likelion.mutsasns.domain.Response;
import com.likelion.mutsasns.domain.dto.UserDto;
import com.likelion.mutsasns.domain.dto.UserJoinRequest;
import com.likelion.mutsasns.domain.dto.UserJoinResponse;
import com.likelion.mutsasns.domain.dto.UserLoginRequest;
import com.likelion.mutsasns.service.UserService;
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
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest userJoinRequest) {
        UserDto userDto = userService.join(userJoinRequest);
        return Response.success(new UserJoinResponse(userDto.getUserName()));
    }

    @PostMapping("/login")
    public Response<String> login(@RequestBody UserLoginRequest userLoginRequest) {
        String token = userService.login(userLoginRequest.getUserName(), userLoginRequest.getPassword());
        //return ResponseEntity.ok().body(token);
        return Response.login_success(token);
    }
}