package com.likelion.mutsasns.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// no annotation, Pojo!
class SumOfDigitServiceTest {

    SumOfDigitService sumOfDigitService;

    @Test
    @DisplayName("자릿수 합 로직 결과가 기댓값과 일치하는가?")
    void sumOfDigit() {
        assertEquals(21, sumOfDigitService.sum(687));
        assertEquals(10, sumOfDigitService.sum(1234));
        assertEquals(0, sumOfDigitService.sum(0));
        assertEquals(5, sumOfDigitService.sum(11111));
    }
}