package com.likelion.mutsasns.domain.dto;

import com.likelion.mutsasns.domain.entity.BaseEntity;
import com.likelion.mutsasns.domain.entity.Comment;
import com.likelion.mutsasns.domain.entity.Post;
import com.likelion.mutsasns.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentAddRequest {
    //private Long id;
    private String comment;
    //private Post post;
    //private User user;

    /*
    Dto를 Entity로 변환
     */
    public Comment toEntity() {
        Comment commentEntity = Comment.builder()
                .comment(this.comment)
                .build();
        return commentEntity;
    }
}
