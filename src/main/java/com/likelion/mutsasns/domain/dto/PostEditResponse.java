package com.likelion.mutsasns.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostEditResponse {
    private Long UpdatedPostId;
    private String message;
}