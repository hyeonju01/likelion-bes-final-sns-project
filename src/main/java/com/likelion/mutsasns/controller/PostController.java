package com.likelion.mutsasns.controller;


import com.likelion.mutsasns.domain.entity.Response;
import com.likelion.mutsasns.domain.dto.*;
import com.likelion.mutsasns.domain.dto.CommentEditResponse;
import com.likelion.mutsasns.service.CommentService;
import com.likelion.mutsasns.service.PostService;
import io.swagger.annotations.ApiOperation;
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

    @PostMapping("")
    @ApiOperation(value = "게시글 등록", notes = "게시글을 등록합니다.")
    public ResponseEntity<Response<PostAddResponse>> registerPost(@RequestBody PostAddRequest dto, Authentication authentication) {
        String userName = authentication.getName();
        PostAddResponse postAddResponse = postService.createPost(dto, userName);
        return ResponseEntity.ok().body(Response.success(postAddResponse));
    }

    @GetMapping("/{postsId}")
    @ApiOperation(value = "게시글 1건 조회", notes = "해당 Id를 가지는 게시글을 조회합니다.")
    public ResponseEntity<Response<PostResponse>> getById(@PathVariable Long postsId) {
        PostResponse postResponse = postService.findPostDetailById(postsId);
        return ResponseEntity.ok().body(Response.success(postResponse));
    }

    @GetMapping("")
    @ApiOperation(value = "게시글 목록 조회", notes = "게시글을 20건씩 조회합니다.")
    public ResponseEntity<Response<PostListResponse>> getAll(Pageable pageable) {
        PostListResponse postListResponse = postService.list(pageable);
        return ResponseEntity.ok().body(Response.success(postListResponse));
    }

    @PutMapping("/{postId}")
    @ApiOperation(value = "게시글 수정", notes = "해당 Id를 가지는 게시글을 수정합니다.")
    public ResponseEntity<Response<PostEditResponse>> updatePost(@PathVariable Long postId, @RequestBody PostEditRequest dto) {
        PostEditResponse postEditResponse = postService.edit(postId, dto);
        return ResponseEntity.ok().body(Response.success(postEditResponse));
    }

    @DeleteMapping("/post/{id}")
    @ApiOperation(value = "게시글 삭제", notes = "해당 Id를 가지는 게시글을 삭제합니다.")
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