package com.likelion.mutsasns.domain.dto;

import com.likelion.mutsasns.domain.entity.Post;
import com.likelion.mutsasns.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class PostAddRequest {
    private String title; //포스트 제목
    private String body; //포스트 내용
    //private User user;

    public Post toEntity(User user) {
        Post post = Post.builder()
                .title(this.getTitle())
                .body(this.getBody())
                .user(user)
                .build();
        return post;
    }
}
