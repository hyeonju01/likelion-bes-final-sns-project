package com.likelion.mutsasns.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentEditResponse {
    private Long id;
    private String userName;
    private Long postId;
    private String modifiedComment;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
}
