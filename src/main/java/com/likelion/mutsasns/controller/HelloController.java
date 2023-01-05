package com.likelion.mutsasns.controller;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "이현주";
    }


}