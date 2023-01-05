package com.likelion.mutsasns.domain.entity;

import com.likelion.mutsasns.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id 생성 전략은 DB에 위임
    private Long id;

    @Column(unique = true) //userName은 고유해야한다.
    private String userName;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}