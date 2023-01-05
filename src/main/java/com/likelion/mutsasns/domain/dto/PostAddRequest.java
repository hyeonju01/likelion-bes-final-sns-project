package com.likelion.mutsasns.domain.dto;

import com.likelion.mutsasns.domain.entity.Post;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class PostAddRequest {
    private String title; //포스트 제목
    private String body; //포스트 내용

    public Post toEntity() {
        Post post = Post.builder()
                .title(this.title)
                .body(this.body)
                .build();
        return post;
    }
}
