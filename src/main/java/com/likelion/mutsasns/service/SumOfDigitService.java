package com.likelion.mutsasns.service;

public class SumOfDigitService {

    public int sum(int num) {
        int sum = 0;

        while (num / 10 != 0) {
            sum = sum + num % 10;
            num = num / 10;
        }

        // sum 기능 구현
        return sum;
    }

}
