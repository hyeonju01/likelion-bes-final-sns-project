package com.likelion.mutsasns.domain.dto;

import com.likelion.mutsasns.domain.Post;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class PostEditRequest {
    private String modifiedTitle; //포스트 제목
    private String modifiedBody; //포스트 내용

    public Post toEntity() {
        Post modifiedPost = Post.builder()
                .title(this.modifiedTitle)
                .body(this.modifiedBody)
                .build();
        return modifiedPost;
    }

}
