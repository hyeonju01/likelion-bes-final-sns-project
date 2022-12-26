package com.likelion.mutsasns.repository;

import com.likelion.mutsasns.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
