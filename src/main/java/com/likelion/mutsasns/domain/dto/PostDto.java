package com.likelion.mutsasns.domain.dto;

import com.likelion.mutsasns.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostDto {
    private Long id;
    private String title;
    private String body;

    // Post 객체 생성 메서드
    public Post toEntity() {
        return new Post(title, body);
    }
}