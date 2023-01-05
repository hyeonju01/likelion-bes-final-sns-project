package com.likelion.mutsasns.domain.dto;

import com.likelion.mutsasns.domain.entity.Comment;
import com.likelion.mutsasns.domain.entity.Post;
import com.likelion.mutsasns.domain.entity.User;

import java.time.LocalDateTime;

public class CommentAddResponse {
    private Long id;
    private String comment;
    //private Post post;
    private Long postId;
    //private User user;
    private String userName;
    private LocalDateTime createdAt;

    /*
    entity 를 Dto로
     */

    public CommentAddResponse(Long postId, Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.postId = postId;
        this.userName = comment.getUser().getUserName();
        this.createdAt = comment.getCreatedAt();
    }

}
