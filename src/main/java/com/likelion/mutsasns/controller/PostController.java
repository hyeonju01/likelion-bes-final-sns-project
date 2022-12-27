package com.likelion.mutsasns.controller;


import com.likelion.mutsasns.domain.Response;
import com.likelion.mutsasns.domain.dto.*;
import com.likelion.mutsasns.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    // 📌
    // [ V ] 포스트 등록 기능 구현
    // [ v ] Swagger Test Passed
    // [ v ] return 형식 맞추기
    @PostMapping("")
    public ResponseEntity<Response<PostAddResponse>> registerPost(@RequestBody PostAddRequest dto) {
        PostAddResponse postAddResponse = postService.add(dto);
        return ResponseEntity.ok().body(Response.success(postAddResponse));
    }

    // 📌
    // [ v ] 포스트 조회 기능 (1건)
    // [ v ] Swagger Test Passed
    // [ v ] return 형식 맞추기
    @GetMapping("/{postsId}")
    public ResponseEntity<Response<PostResponse>> getById(@PathVariable Long postsId) {
        PostResponse postResponse = postService.getById(postsId);
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
    @DeleteMapping("/post/{id}")
    public ResponseEntity<Response<PostDeleteResponse>> deletePost(@PathVariable Long id) {
        PostDeleteResponse postDeleteResponse = postService.deletePostById(id);
        return ResponseEntity.ok().body(Response.success(postDeleteResponse));
    }
}