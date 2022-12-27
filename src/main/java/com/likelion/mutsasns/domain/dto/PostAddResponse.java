package com.likelion.mutsasns.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class PostAddResponse {
    private Long id;
    private String title;
    private String body;
}
