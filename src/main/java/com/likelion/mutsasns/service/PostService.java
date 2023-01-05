package com.likelion.mutsasns.service;

import com.likelion.mutsasns.domain.entity.Post;
import com.likelion.mutsasns.domain.dto.*;
import com.likelion.mutsasns.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    // 포스트 등록
    public PostAddResponse add(PostAddRequest dto) {
        Post post = dto.toEntity();
        Post registerdPost = postRepository.save(post);
        PostAddResponse postAddResponse = new PostAddResponse(registerdPost.getId(), "포스트 등록 완료");
        return postAddResponse;
    }

    // 포스트 상세 (1개 조회)
    public PostResponse findPostDetailById (Long id) {
        //서비스 로직 추가
        Optional<Post> postEntityWrapper = postRepository.findById(id);
        // findById()의 반환값이 Optional이므로 get()을 하여 Post엔티티를 추출!
        Post post = postEntityWrapper.get();
        return PostResponse.of(post);
    }

    // 포스트 리스트 (최신 순, 20개씩)
    public PostListResponse list(@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        //서비스 로직 추가
        Page<Post> posts = postRepository.findAll(pageable);
        List<PostResponse> postResponses = posts.stream()
                                                .map(post -> PostResponse.of(post))
                                                .collect(Collectors.toList());
        PostListResponse postListResponse = new PostListResponse(postResponses);
        return postListResponse; // list 객체 반환
    }

    // 포스트 수정
    public PostEditResponse edit(Long postId, PostEditRequest dto) {

        Post updatePost = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException());
        updatePost.setTitle(dto.getModifiedTitle());
        updatePost.setBody(dto.getModifiedBody());
        updatePost.setLastModifiedAt(dto.toEntity().getLastModifiedAt());

        postRepository.save(updatePost);

        PostEditResponse postEditResponse = new PostEditResponse(postId, "포스트 수정 완료");

        return postEditResponse;
    }

    // 포스트 삭제
    public PostDeleteResponse deletePostById(Long id) {

        postRepository.deleteById(id);

        PostDeleteResponse postDeleteResponse = new PostDeleteResponse(id, "포스트 삭제 완료");

        return postDeleteResponse;
    }

}
