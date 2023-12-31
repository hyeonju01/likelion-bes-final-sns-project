package com.likelion.mutsasns.domain.dto;

import com.likelion.mutsasns.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserJoinRequest {

    private String userName;
    private String password;

    public User toEntity() {
        return User.builder()
                .userName(userName)
                .password(password)
                .build();
    }

}