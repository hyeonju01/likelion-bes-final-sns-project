package com.likelion.mutsasns.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelion.mutsasns.annotation.WithMockCustomUser;
import com.likelion.mutsasns.domain.dto.CommentAddRequest;
import com.likelion.mutsasns.domain.dto.CommentAddResponse;
import com.likelion.mutsasns.domain.dto.PostAddRequest;
import com.likelion.mutsasns.domain.dto.PostAddResponse;
import com.likelion.mutsasns.domain.entity.Post;
import com.likelion.mutsasns.exception.ErrorCode;
import com.likelion.mutsasns.exception.SnsException;
import com.likelion.mutsasns.service.CommentService;
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
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
- [ V ] 성공, 실패 테스트 성공
- [ ] 댓글 등록, 실패(2가지) 테스트
 */

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    PostService postService;

    @MockBean
    CommentService commentService;

    PostAddRequest postAddRequest = new PostAddRequest("title", "body");
    PostAddResponse postAddResponse = new PostAddResponse(1L, "포스트 등록 완료"); //미리 만들어둔 response 객체
    @Test
    @DisplayName("포스트 작성 성공")
    @WithMockCustomUser
    void post_add_success() throws Exception{

        given(postService.createPost(postAddRequest, "user")).willReturn(postAddResponse);

        mockMvc.perform(
                post("/api/v1/posts")
                                    .with(csrf())
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsBytes(postAddRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result.createdPostId").value(1L))
                .andExpect(jsonPath("$.result.message").value("포스트 등록 완료"))
                .andDo(print());

        //verify(postService).createPost(postAddRequest, "user");

    }

//    @Test
//    @DisplayName("포스트 작성 성공")
//    @WithMockUser(username = "hyeonju", password = "1234")
//    void post_success() throws Exception {
//
//        // given
//        // Request 객체 생성
//        PostAddRequest dto = new PostAddRequest("제목", "내용", "hyeonju");
//
//        // when
//        // mock test 코드 작성
//        when(postService.add(dto)).thenReturn(new PostAddResponse(1L, dto.getTitle()));
//
//        // then
//        // 검증
//        mockMvc.perform(post("/api/v1/posts")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(dto))
//                        )
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.result.createdPostId").exists())
//                .andExpect(jsonPath("$.result.message").exists())
//                .andDo(print());
//
//        verify(postService).add(dto);
//    }

    @Test
    @DisplayName("포스트 작성 실패 - 로그인 상태가 아닐 경우")
    @WithAnonymousUser
    void post_fail() throws Exception {
        PostAddRequest dto = new PostAddRequest("제목", "내용");

        //given(postService.add(any())).willReturn(new PostAddResponse(1l, dto.getTitle(), dto.getBody()));
        when(postService.createPost(any(), any())).thenThrow(new SnsException(ErrorCode.INVALID_PERMISSION, ""));

        mockMvc.perform(post("/api/v1/posts")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(dto))
                        )
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @DisplayName("포스트 1건 조회 성공")
    void post_detail_success() {
    }

    @Test
    @DisplayName("포스트 작성 실패 케이스 (인증 실패, Jwt를 Bearer Token으로 보내지 않음)")
    void post_add_fail1() {
    }

    @Test
    @DisplayName("포스트 작성 실패 케이스 (인증 실패, Jwt 토큰이 유효하지 않음)")
    void post_add_fail2() {
    }

    @Test
    @DisplayName("포스트 수정 성공")
    void post_edit_success() {
    }
    @Test
    @DisplayName("포스트 수정 실패: 인증 실패")
    void post_edit_fail1() {
    }
    @Test
    @DisplayName("포스트 수정 실패 : 작성자 불일치")
    void post_edit_fail2() {
    }

    @Test
    @DisplayName("포스트 수정 실패 : DB 에러")
    void post_edit_fail3() {
    }

    @Test
    @DisplayName("포스트 삭제 성공")
    void post_delete_success() {
    }

    @Test
    @DisplayName("포스트 삭제 실패: 인증 실패")
    void post_delete_fail1() {
    }

    @Test
    @DisplayName("포스트 삭제 실패 : 작성자 불일치")
    void post_delete_fail2() {
    }

    @Test
    @DisplayName("포스트 삭제 실패 : DB 에러")
    void post_delete_fail3() {
    }

    @Test
    @DisplayName("포스트 목록 조회 - 작성일 최신순으로 조회가 되는지")
    void post_list_success() {
    }

    @Test
    @WithMockUser(username = "hyeonju", password = "1234")
    @DisplayName("댓글 작성 성공")
    void comment_success() throws Exception {
        CommentAddRequest dto = new CommentAddRequest("댓글");
        Post post = new Post("제목", "내용");

        //when(commentService.add(post.getId(), dto)).thenReturn(new CommentAddResponse(post.getId(), dto.toEntity())); 이거 넣으면 안뜬다 ㅠㅠ

        mockMvc.perform(post("/api/v1/posts/1/comments")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(dto))
                        )
                .andExpect(status().isOk())
                //.andExpect(jsonPath("").exists())//postId=10인 포스트에
                //.andExpect(jsonPath("").exists())//postId=10인 포스트에
                //.andExpect(jsonPath("").exists())
                .andDo(print());
    }

//    @Test
//    @WithAnonymousUser
//    @DisplayName("댓글 작성 실패: 로그인하지 않은 경우")
//    void comment_fail1() {
//
//        mockMvc.perform(post("/api/v1/posts/1/comments")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes())
//                        )
//                .andExpect(status().isUnauthorized())
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("댓글 작성 실패: 게시물이 존재하지 않는 경우")
//    void comment_fail2() {
//
//
//        mockMvc.perform(post("/api/v1/posts/1/comments")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes())
//                )
//                .andExpect(ErrorCode.POST_NOT_FOUND)
//                .andDo(print());
//    }



//    @Test
//    @DisplayName("댓글 수정 성공")
//    @WithMockUser
//    void name() throws Exception {
//        /*
//        * 응답 포맷
//    {
//	"resultCode": "SUCCESS",
//	"result":{
//		"id": 4,
//		"comment": "modify comment",
//		"userName": "test",
//		"postId": 2,
//		"createdAt": "2022-12-20T16:15:04.270741",
//		"lastModifiedAt": "2022-12-23T16:15:04.270741"
//		}
//    }
//     */
//        // * endpoint
//        // PUT /posts/{postId}/comments/{id}
//
//        mockMvc.perform(put("/api/v1/posts/1/comments/{id}1")
//                .with(csrf())
//                .contentType(MediaType.APPLICATION_JSON)
//        ).andDo(print())
//                .andExpect(jsonPath("$.result.comment").value("this is comment"))
//                .andExpect(status().isOk());
//    }

    @Test
    @DisplayName("댓글 수정 실패1: 인증 실패")
    void comment_modify_fail1() {
    }

    @Test
    @DisplayName("댓글 수정 실패2: Post가 없음")
    void comment_modify_fail2() {
    }

    @Test
    @DisplayName("댓글 수정 실패3: 작성자 불일치")
    void comment_modify_fail3() {
    }

    @Test
    @DisplayName("댓글 수정 실패4: DB 에러")
    void comment_modify_fail4() {
    }

    @Test
    @DisplayName("댓글 삭제 성공")
    void comment_delete_success() {
    }

    @Test
    @DisplayName("댓글 삭제 실패1: 인증 실패")
    void comment_delete_fail1() {
    }

    @Test
    @DisplayName("댓글 삭제 실패2: Post가 없음")
    void comment_delete_fail2() {
    }

    @Test
    @DisplayName("댓글 삭제 실패3: 작성자 불일치")
    void comment_delete_fail3() {
    }

    @Test
    @DisplayName("댓글 삭제 실패4: DB 에러")
    void comment_delete_fail4() {
    }

    @Test
    @DisplayName("댓글 목록 조회 성공")
    void comment_list_success() {
    }


}