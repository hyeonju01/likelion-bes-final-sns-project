package com.likelion.mutsasns.controller;


import com.likelion.mutsasns.domain.entity.Response;
import com.likelion.mutsasns.domain.dto.*;
import com.likelion.mutsasns.domain.dto.CommentEditResponse;
import com.likelion.mutsasns.service.CommentService;
import com.likelion.mutsasns.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    private final CommentService commentService;

    // 📌
    // [ V ] 포스트 등록 기능 구현
    // [ v ] Swagger Test Passed
    // [ v ] return 형식 맞추기
    @PostMapping("")
    public ResponseEntity<Response<PostAddResponse>> registerPost(@RequestBody PostAddRequest dto, Authentication authentication) {
        String userName = authentication.getName();
        PostAddResponse postAddResponse = postService.createPost(dto, userName);
        return ResponseEntity.ok().body(Response.success(postAddResponse));
    }

    // 📌
    // [ v ] 포스트 조회 기능 (1건)
    // [ v ] Swagger Test Passed
    // [ v ] return 형식 맞추기
    @GetMapping("/{postsId}")
    public ResponseEntity<Response<PostResponse>> getById(@PathVariable Long postsId) {
        PostResponse postResponse = postService.findPostDetailById(postsId);
        return ResponseEntity.ok().body(Response.success(postResponse));
    }

    // 📌
    // [ v ] 포스트 조회 기능 (List, 20개)
    // [ v ] Swagger Test Passed
    // [ v ] return 형식 맞추기
    @GetMapping("")
    public ResponseEntity<Response<PostListResponse>> getAll(Pageable pageable) {
        PostListResponse postListResponse = postService.list(pageable);
        return ResponseEntity.ok().body(Response.success(postListResponse));
    }

    // 📌
    // [ v ] 포스트 수정 기능 구현
    // [ v ] Swagger Test Passed
    // [ v ] return 형식 맞추기
    @PutMapping("/{postId}")
    public ResponseEntity<Response<PostEditResponse>> updatePost(@PathVariable Long postId, @RequestBody PostEditRequest dto) {

        PostEditResponse postEditResponse = postService.edit(postId, dto);

        return ResponseEntity.ok().body(Response.success(postEditResponse));
    }

    // 📌
    // [ v ] 포스트 삭제 기능
    // [ v ] Swagger Test Passed
    // [ v ] return 형식 맞추기
    // [  ] 포스트 삭제 시 포스트에 달린 댓글도 모두 삭제하는 기능 추가
    @DeleteMapping("/post/{id}")
    public ResponseEntity<Response<PostDeleteResponse>> deletePost(@PathVariable Long id) {
        PostDeleteResponse postDeleteResponse = postService.deletePostById(id);
        return ResponseEntity.ok().body(Response.success(postDeleteResponse));
    }

    // 📌
    // [  ] 댓글 작성
    // [  ] Passed Swagger Test
    // [  ] 리턴 형식 올바르게 나오는지 확인
    @PostMapping("/{postId}/comments") //full uri: /api/v1/posts/{postId}/comments
    public ResponseEntity<Response<CommentAddResponse>> makeComment(@PathVariable Long postId, @RequestBody CommentAddRequest commentAddRequest) {
        CommentAddResponse commentAddResponse = commentService.add(postId, commentAddRequest);
        return ResponseEntity.ok().body(Response.success(commentAddResponse));
    }

    // 📌****** 기능 구현 구글링 필요
    // [  ] 댓글 목록 조회
    // [  ] Passed Swagger Test
    // [  ] 리턴 형식 올바르게 나오는지 확인
//    @GetMapping("/{postId}/comments[?page=0]")
//    public ResponseEntity<Response<CommentListRequest>> selectCommentList(@Param) {
//        CommentListRequest commentListRequest = commentService.list(pageable)
//    }


    // 📌***** 기능 구현 구글링 필요
    // [  ] 댓글 수정
    // [  ] Passed Swagger Test
    // [  ] 리턴 형식 올바르게 나오는지 확인
//    @PutMapping("/{postId}/comments/{id}")
//    public ResponseEntity<Response<CommentEditResponse>> commentModify() {
//        CommentEditRequest commentEditRequest;
//        CommentEditResponse commentEditResponse = commentService.updateComment(@PathVariable Long commentId, )
//        return ResponseEntity.ok().body(Response.success());
//    }


    // 📌
    // [  ] 댓글 삭제
    // [  ] Passed Swagger Test
    // [  ] 리턴 형식 올바르게 나오는지 확인
//    @DeleteMapping("/{postId}/comments/{id}")

}