package com.likelion.mutsasns.service;

import com.likelion.mutsasns.domain.dto.CommentAddRequest;
import com.likelion.mutsasns.domain.dto.CommentAddResponse;
import com.likelion.mutsasns.domain.entity.Comment;
import com.likelion.mutsasns.domain.dto.CommentEditRequest;
import com.likelion.mutsasns.domain.dto.CommentEditResponse;
import com.likelion.mutsasns.repository.CommentRepository;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    // 댓글 등록
    public CommentAddResponse add(Long postId, CommentAddRequest dto) {
        Comment comment = dto.toEntity();
        Comment registeredComment = commentRepository.save(comment); //save메서드의 결과, comment 객체 반환
        CommentAddResponse commentAddResponse= new CommentAddResponse(postId, registeredComment); //
        return commentAddResponse;
    }

    // 댓글 수정
    public CommentEditResponse updateComment(Long commentId, CommentEditRequest commentEditRequest) {
        return null;
    }

}
