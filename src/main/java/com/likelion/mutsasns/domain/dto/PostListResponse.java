package com.likelion.mutsasns.domain.dto;

import com.likelion.mutsasns.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostListResponse {

    private List<PostResponse> content;

}
