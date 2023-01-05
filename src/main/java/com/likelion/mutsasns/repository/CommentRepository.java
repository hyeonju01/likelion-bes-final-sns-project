package com.likelion.mutsasns.repository;

import com.likelion.mutsasns.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
