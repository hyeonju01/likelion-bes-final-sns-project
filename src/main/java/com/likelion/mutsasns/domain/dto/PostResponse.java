package com.likelion.mutsasns.domain.dto;

import com.likelion.mutsasns.domain.Post;
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

    public static PostResponse of(Optional<Post> post) {
        return PostResponse.builder()
                .postId(post.get().getId())
                .title(post.get().getTitle())
                .body(post.get().getBody())
                .userName(post.get().getUser().getUserName())
                .createdAt(post.get().getCreatedAt())
                .lastModifiedAt(post.get().getLastModifiedAt())
                .build();
    }
}
