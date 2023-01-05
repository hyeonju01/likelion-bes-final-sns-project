package com.likelion.mutsasns.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 댓글 id
    private String comment; //내용

    //양방향 mapping 필요
    //일단 지연로딩으로
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post; // 포스트 id

    //양방향 mapping 필요
    //일단 지연로딩으로
    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // 댓글 작성자 id
}
