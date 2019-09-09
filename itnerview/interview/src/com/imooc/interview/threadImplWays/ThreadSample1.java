package com.imooc.interview.threadImplWays;

import java.util.Random;

public class ThreadSample1 {
    public static void main(String[] args) {
        Runner1 r1 = new Runner1();
        r1.setName("张三");
        Runner1 r2 = new Runner1();
        r2.setName("李四");
        Runner1 r3 = new Runner1();
        r3.setName("王五");
        r1.start();
        r2.start();
        r3.start();
    }

}
class Runner1 extends Thread{
    @Override
    public void run(){
        Integer speed = new Random().nextInt(10) + 1;
        for (int i = 1; i <= 100; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName() + "已前进" + (i * speed) + "米");
        }
    }
}