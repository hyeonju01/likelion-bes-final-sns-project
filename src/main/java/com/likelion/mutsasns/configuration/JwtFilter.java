package com.likelion.mutsasns.configuration;

import com.likelion.mutsasns.service.UserService;
import com.likelion.mutsasns.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authentication: {}", authorization);

        /*
        - doFilter: token이 존재하지 않거나 Bearer로 시작하지 않을 경우 접근 금지
         */
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.error("authentication을 잘못 보냈습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        // token 추출
        String token = authorization.split(" ")[1];

        /*
        - doFilter: token 발급일시를 검증하여 만료 여부 확인, 만료일 경우 접근 금지
         */
        if (JwtTokenUtil.isExpired(token, secretKey)) {
            log.error("token이 만료되었습니다. ");
            filterChain.doFilter(request, response);
            return;
        }

        // token으로부터 사용자 id 추출
        String userName = JwtTokenUtil.getUserName(token, secretKey);
        log.info("userName: {}", userName);

        // 인가 (권한 부여)
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        userName,
                        null,
                        List.of(new SimpleGrantedAuthority("USER")));
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
