package com.likelion.mutsasns.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelion.mutsasns.domain.dto.PostAddRequest;
import com.likelion.mutsasns.domain.dto.PostAddResponse;
import com.likelion.mutsasns.exception.ErrorCode;
import com.likelion.mutsasns.exception.SnsException;
import com.likelion.mutsasns.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
- [ V ] 성공, 실패 테스트 성공
 */

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    PostService postService;

    @Test
    @DisplayName("포스트 작성 성공")
    @WithMockUser(username = "hyeonju", password = "1234")
    void post_success() throws Exception {

        //Request 객체 생성
        PostAddRequest dto = new PostAddRequest("제목", "내용");

        //mock test 코드 작성
        //when(postService.add(any())).thenReturn(new PostAddResponse(1L, dto.getTitle(), dto.getBody()));

        // 검증
        mockMvc.perform(post("/api/v1/posts")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(dto))
                        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.body").exists())
                .andDo(print());

        verify(postService).add(dto);
    }

    @Test
    @DisplayName("포스트 작성 실패 - 로그인 상태가 아닐 경우")
    @WithAnonymousUser
    void post_fail() throws Exception {
        PostAddRequest dto = new PostAddRequest("제목", "내용");

        //given(postService.add(any())).willReturn(new PostAddResponse(1l, dto.getTitle(), dto.getBody()));
        when(postService.add(any())).thenThrow(new SnsException(ErrorCode.INVALID_PERMISSION, ""));

        mockMvc.perform(post("/api/v1/posts")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(dto))
                        )
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

}