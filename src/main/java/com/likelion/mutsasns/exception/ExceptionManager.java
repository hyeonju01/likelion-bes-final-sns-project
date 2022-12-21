package com.likelion.mutsasns.exception;

import com.likelion.mutsasns.domain.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/*
[  ] 22.12.22 ErrorResponse 이렇게 하는 것 맞나....잘 모르겠다
[ V ] 22.12.21 프로젝트 로그에 강사님이 올려주신 코드와 비교
 */
@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(SnsException.class)
    public ResponseEntity<?> SnsExceptionHandler(SnsException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getMessage());
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error("ERROR", errorResponse));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error("ERROR", e.getMessage()));
    }
}
