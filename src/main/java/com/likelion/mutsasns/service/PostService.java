package com.likelion.mutsasns.service;

import com.likelion.mutsasns.domain.Post;
import com.likelion.mutsasns.domain.dto.*;
import com.likelion.mutsasns.repository.PostRepository;
import com.likelion.mutsasns.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service

public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

//    public PostService(PostRepository postRepository, UserRepository userRepository) {
//        this.postRepository = postRepository;
//        this.userRepository = userRepository;
//    }

    // 포스트 등록
    public PostAddResponse add(PostAddRequest dto) {
        Post post = dto.toEntity();
        Post registerdPost = postRepository.save(post);
        return new PostAddResponse(registerdPost.getId(),
                registerdPost.getTitle(), registerdPost.getBody());
    }

    // 포스트 상세 (1개 조회)
    public PostResponse getById(Long id) {
        //서비스 로직 추가
        Optional<Post> OptionalPost = postRepository.findById(id);
        return PostResponse.of(OptionalPost);
    }

    // 포스트 리스트 (최신 순, 20개씩)
    // 컴파일에러 수정하기
//    public List<PostResponse> list(Pageable pageable) {
//        //서비스 로직 추가
//        Page<Post> posts = postRepository.findAll(pageable);
//        List<PostResponse> postResponses = posts.stream()
//                                                .map(post -> PostResponse.of(posts))
//                                                .collect(Collectors.toList());
//        return postResponses; // list 객체 반환
//    }

    // 포스트 수정
    public PostEditResponse edit(PostEditRequest dto) {
        return new PostEditResponse();
    }

    // 포스트 삭제
    public PostDeleteResponse delete(PostDeleteRequest dto) {

        return new PostDeleteResponse();
    }
}
