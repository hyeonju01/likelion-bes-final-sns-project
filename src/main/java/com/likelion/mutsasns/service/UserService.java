package com.likelion.mutsasns.service;

import com.likelion.mutsasns.domain.User;
import com.likelion.mutsasns.domain.dto.UserDto;
import com.likelion.mutsasns.domain.dto.UserJoinRequest;
import com.likelion.mutsasns.exception.ErrorCode;
import com.likelion.mutsasns.exception.SnsException;
import com.likelion.mutsasns.repository.UserRepository;
import com.likelion.mutsasns.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value; //lombok의 Value 어노테이션과 헷깔리지 않도록 한다.
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.secret}")
    private String secretKey;
    private final long expiredTimeMs = 1000 * 60 * 60L;

    // 회원가입 서비스 구현부
    public UserDto join(UserJoinRequest req) {

        userRepository.findByUserName(req.getUserName())
                .ifPresent(user -> {
                    throw new SnsException(ErrorCode.DUPLICATED_USER_NAME, String.format("userName: %s", req.getUserName())); //2nd Parameter 확인필요
                });

        String password = encoder.encode(req.getPassword());
        User savedUser = userRepository.save(req.toEntity(password));

        return UserDto.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .password(savedUser.getPassword())
                .build();
    }

    // 로그인 서비스 구현부
    public String login(String userName, String password) {
        /*
        UserService 자체에서 error 처리하는 기능
        (1) case 1: userName 없는 경우
        (2) case 2: password 불일치
         */
        User loginUserInfo = userRepository.findByUserName(userName)
                .orElseThrow(() -> new SnsException(ErrorCode.USERNAME_NOT_FOUND, String.format("%s이 없습니다.", userName)));
        if (!encoder.matches(password, loginUserInfo.getPassword())) {
            throw new SnsException(ErrorCode.INVALID_PASSWORD, "");
        }

        String token = JwtTokenUtil.createToken(loginUserInfo.getUserName(), secretKey, expiredTimeMs);

        return token;
    }
}
