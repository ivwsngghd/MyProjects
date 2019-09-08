package com.imooc.interview;

import java.util.Random;

public class RandomSample {
    public Integer randomInt1(){
        int min = 30;
        int max = 100;
        int result = new Random().nextInt(max - min) + min;
        return result;
    }

    public Integer randomInt2(){
        int min = 30;
        int max = 100;
        int result = (int)(Math.random() * (max-min)) + min;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new RandomSample().randomInt1());
        System.out.println(new RandomSample().randomInt2());
    }
}
