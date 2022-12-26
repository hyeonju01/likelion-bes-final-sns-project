package com.likelion.mutsasns.service;

import com.likelion.mutsasns.domain.Post;
import com.likelion.mutsasns.domain.dto.PostAddRequest;
import com.likelion.mutsasns.domain.dto.PostAddResponse;
import com.likelion.mutsasns.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostAddResponse add(PostAddRequest dto) {
        Post post = dto.toEntity();
        Post registerdPost = postRepository.save(post);
        return new PostAddResponse(registerdPost.getId(),
                registerdPost.getTitle(), registerdPost.getBody());
    }

}
