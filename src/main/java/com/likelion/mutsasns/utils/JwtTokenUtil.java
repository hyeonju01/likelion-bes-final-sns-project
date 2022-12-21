/*
[ ] 주석 정리하기
 */

package com.likelion.mutsasns.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;


public class JwtTokenUtil {

    // userName 파싱
    public static String getUserName(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().get("userName", String.class);
    }

    // claims에서 추출?
    private static Claims extractClaims(String token, String key) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

    // 토큰 만료여부 판단
    public static boolean isExpired(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().getExpiration().before(new Date());
    }

    // 토큰 발급
    public static String createToken(String userName, String key, long expireTimeMs) {
        Claims claims = Jwts.claims();
        claims.put("userName", userName);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis())) //토큰 발급일시 설정
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs)) //토큰 만료일시 설정
                .signWith(SignatureAlgorithm.HS256, key) //키 암호화 알고리즘은 HS256을 사용
                .compact()
                ;
    }
}