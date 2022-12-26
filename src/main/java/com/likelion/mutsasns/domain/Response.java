package com.likelion.mutsasns.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*
22. 12. 21 변경
 */
@AllArgsConstructor
@Getter
public class Response<T> {

    private String resultCode;
    private T result;

    public static <T> Response<T> error(String resultCode,  T result) {
        return new Response(resultCode, result);
    }

    public static <T> Response<T> success(T result) {
        return new Response<>("SUCCESS",  result);
    }

    public static <T> Response<T> login_success(T result) {
        return new Response<>("jwt",  result);
    }

    public static Response<Void> success() {
        return new Response("SUCCESS",  null);
    }
}