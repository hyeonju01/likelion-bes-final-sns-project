package com.likelion.mutsasns.controller;


import com.likelion.mutsasns.domain.Post;
import com.likelion.mutsasns.domain.Response;
import com.likelion.mutsasns.domain.dto.*;
import com.likelion.mutsasns.repository.PostRepository;
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

    private final PostRepository postRepository;

//    // di -> @AllArgsConstructor 어노테이션으로 생략
//    public PostController(PostService postService, PostRepository postRepository) {
//        this.postService = postService;
//        this.postRepository = postRepository;
//    }

    // [ V ] 포스트 등록 기능 구현
    // [   ] return Response 형식 수정필요
    @PostMapping("")
    public ResponseEntity<Response<PostAddResponse>> registerPost(@RequestBody PostAddRequest dto) {
        PostAddResponse response = postService.add(dto);
        return ResponseEntity.ok().body(Response.success(response));
    }

    // [  ] 포스트 조회 기능 (1건) - 구현중
    // 테스트 필요
    @GetMapping("/{postsId}")
    public ResponseEntity<Response<PostResponse>> getById(@PathVariable Long postsId) {
        PostResponse postResponse = postService.getById(postsId);
        return ResponseEntity.ok().body(Response.success(postResponse));
    }

//    // [ V ] 포스트 조회 기능 (List, 20개)
//    @GetMapping("")
//    public ResponseEntity<List<PostResponse>> getAll(Pageable pageable) {
//        return ResponseEntity.ok().body(postService.list(pageable));
//    }

    // [  ] 포스트 수정 기능 대충 틀만 짬 - 구현중
//    @PutMapping("{postsId}")
//    public ResponseEntity<PostEditResponse> editPost(@PathVariable Long postsId, @RequestBody PostEditRequest dto) {
//        Optional<Post> postOptional = postRepository.findById(postsId);
//        if (postOptional.isPresent()) { // postOptional이 있으면 수정
//            PostEditResponse response = postService.edit(postsId, dto); // 파라미터 세 개 필요한거 맞나?
//        } else{
//            return "error";
//        }
//    }

    // [ v ] 포스트 삭제 기능 - 구현
    // [   ] return 형식 맞추기
    @DeleteMapping("/post/{id}")
    void delete(@PathVariable Long id) {
        postRepository.deleteById(id);
    }


}
