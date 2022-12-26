package com.likelion.mutsasns.domain;

import com.likelion.mutsasns.domain.dto.PostDto;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Post extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;

    // [ ] User Entity와 mapping 필요
    //private Long user_id;

    // [V] of 메서드
    public static PostDto of(Post post) {
        return new PostDto(post.getId(), post.getTitle(), post.getBody());
    }
}