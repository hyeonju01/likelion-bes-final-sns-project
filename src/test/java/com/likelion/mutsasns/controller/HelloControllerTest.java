package com.likelion.mutsasns.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelion.mutsasns.service.SumOfDigitService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(HelloController.class)
class HelloControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    SumOfDigitService sumOfDigitService;


    @Test
    @WithMockUser
    @DisplayName("hello 성공")
    void hello() throws Exception {
        mockMvc.perform(get("api/v1/hello")
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    @DisplayName("자릿수 합 결과가 잘 나오는가? ")
    void sum_of_digit_success() throws Exception {

        // service를 주입받아서 controller가 잘 동작하는지 테스트 (service는 따로 테스트한다.)
        when(sumOfDigitService.sum(any()))
                .thenReturn(10);

        mockMvc.perform(get("api/v1/hello/1234")
                        .with(csrf()))
                        .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(10)));
    }
}