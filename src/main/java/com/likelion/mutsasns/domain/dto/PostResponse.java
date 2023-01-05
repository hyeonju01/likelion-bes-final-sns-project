package com.likelion.mutsasns.domain.dto;

import com.likelion.mutsasns.domain.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

/*
필드 그대로 PostAddResponse에 모두 선언하고 이거삭제해도 될듯
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private Long postId; //10
    private String title; // 글 제목
    private String body; // 글 본문
    private String userName; // 작성자 명
    private LocalDateTime createdAt; //작성일
    private LocalDateTime lastModifiedAt; //최종수정일

    public static PostResponse of(Post post) {
        return PostResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .body(post.getBody())
                .userName(post.getUser().getUserName())
                .createdAt(post.getCreatedAt())
                .lastModifiedAt(post.getLastModifiedAt())
                .build();
    }
}
