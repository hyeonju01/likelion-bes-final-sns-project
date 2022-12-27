package com.likelion.mutsasns.domain;

import com.likelion.mutsasns.domain.dto.PostDto;
import lombok.*;

import javax.persistence.*;

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

    // [ V ] User Entity와 mapping 필요
    // 작성자가 동일한 포스트 여러 개는
    // 한 명의 작성자에게 속한다. -> ManyToOne
    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    // PostDto를 위한 생성자
    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    // [V] of 메서드
    public static PostDto of(Post post) {
        return new PostDto(post.getId(), post.getTitle(), post.getBody());
    }
}