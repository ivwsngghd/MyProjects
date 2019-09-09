package com.imooc.interview.threadSynchronized;

public class ThreadTest {
    private static void attack() {
        System.out.println("Fight");
        System.out.println("Current Thread is : " + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(){
            public void run(){
                attack();
            }
        };
        System.out.println("current main threadSynchronized is : " + Thread.currentThread().getName());
        t.start();
        t.join();
        t.start();
    }
}
