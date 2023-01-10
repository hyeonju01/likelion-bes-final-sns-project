package com.likelion.mutsasns.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelion.mutsasns.domain.dto.UserDto;
import com.likelion.mutsasns.domain.dto.UserJoinRequest;
import com.likelion.mutsasns.domain.dto.UserJoinResponse;
import com.likelion.mutsasns.domain.dto.UserLoginRequest;
import com.likelion.mutsasns.exception.ErrorCode;
import com.likelion.mutsasns.exception.SnsException;
import com.likelion.mutsasns.fixture.TestUserFixture;
import com.likelion.mutsasns.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    String userName = TestUserFixture.get().getUserName();
    String password = TestUserFixture.get().getPassword();

    @Test
    @DisplayName("회원가입 성공")
    @WithMockUser
    public void join_success() throws Exception {
        // given
        UserJoinRequest userJoinRequest = UserJoinRequest.builder()
                .userName(userName)
                .password(password)
                .build();
        UserJoinResponse userJoinResponse = new UserJoinResponse(TestUserFixture.get().getUserId(), userName);
        //when(userService.join(any())).thenReturn(userJoinResponse);
        given(userService.join(any(UserJoinRequest.class))).willReturn(userJoinResponse);

        // when
        mockMvc.perform(post("/api/v1/users/join")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userJoinRequest)))
                .andDo(print())
                .andExpect(jsonPath("$.userName").value(userName))
                .andExpect(status().isOk());

        // then
        //verify(userService).join(refEq(userJoinRequest)); // 통과
        assertEquals(userService.join(userJoinRequest), userJoinResponse);
    }

    @Test
    @DisplayName("회원가입 실패 - UserName 중복")
    @WithMockUser
    public void join_fail_duplicated_user_name() throws Exception {
        // given
        UserJoinRequest userJoinRequest = UserJoinRequest.builder()
                .userName(userName)
                .password(password)
                .build();
        when(userService.join(any())).thenThrow(new SnsException(ErrorCode.DUPLICATED_USER_NAME, ""));

        // when - then
        mockMvc.perform(post("/api/v1/users/join")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(userJoinRequest)))
                .andDo(print())
                .andExpect(status().isConflict());

        assertThrows(SnsException.class, () -> {
            userService.join(userJoinRequest);
        });
    }

    @Test
    @DisplayName("로그인 성공")
    @WithMockUser
    public void login_success() throws Exception {

        String userName = "hyeonjulee";
        String password = "1q2w3e4r";

        when(userService.login(any(), any()))
                .thenReturn("token");

        mockMvc.perform(post("/api/v1/users/login")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName, password))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그인 실패 - (1) userName 존재하지 않음 ")
    @WithMockUser
    public void login_fail1() throws Exception {
        String userName = "hyeonjulee";
        String password = "1q2w3e4r";

        when(userService.login(any(), any()))
                .thenThrow(new SnsException(ErrorCode.USERNAME_NOT_FOUND, ""));

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName, password))))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("로그인 실패 - (2) password 불일치 ")
    @WithMockUser
    public void login_fail2() throws Exception {
        String userName = "hyeonjulee";
        String password = "1q2w3e4r";

        when(userService.login(any(), any()))
                .thenThrow(new SnsException(ErrorCode.INVALID_PASSWORD, ""));

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName, password))))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

//    @Test
//    @DisplayName("권한 변경 성공")
//    void changerolemethodname() {
//        // - given
//        Long userId = 1L;
//        String role = "ADMIN";
//        UserRoleRequest userRoleRequest = new UserRoleRequest();
//        UserRoleResponse userRoleResponse = new UserRoleResponse();
//        given(userService.changeRole(userId, role, "user")).willReturn(userRoleResponse);
//
//        // - when
//        mockMvc.perform(post("/api/v1/users/"+userId+"role/change")
//                        .with(csrf())
//                        .content(objectMapper.writeValueAsBytes(userRoleRequest))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect()
//                .andDo(print());
//
//        // - then
//
//    }
}