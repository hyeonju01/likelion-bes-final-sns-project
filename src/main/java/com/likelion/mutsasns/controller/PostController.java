package com.likelion.mutsasns.controller;


import com.likelion.mutsasns.domain.dto.PostAddRequest;
import com.likelion.mutsasns.domain.dto.PostAddResponse;
import com.likelion.mutsasns.repository.PostRepository;
import com.likelion.mutsasns.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    // di
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // [ V ] 포스트 등록 기능 구현
    @PostMapping("")
    public ResponseEntity<PostAddResponse> registerPost(@RequestBody PostAddRequest dto) {
        PostAddResponse response = postService.add(dto);
        return ResponseEntity.ok().body(response);
    }

}
