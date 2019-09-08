package com.imooc.interview.thread;

import java.util.Random;
import java.util.concurrent.*;

public class ThreadSample3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        Runner3 r1 = new Runner3();
        Runner3 r2 = new Runner3();
        Runner3 r3 = new Runner3();
        r1.setName("张三");
        r2.setName("李四");
        r3.setName("王五");
        Future<Long> result1 = threadPool.submit(r1);
        Future<Long> result2 = threadPool.submit(r2);
        Future<Long> result3 = threadPool.submit(r3);
        System.out.println(result1.get());
        System.out.println(result2.get());
        System.out.println(result3.get());
    }
}
class  Runner3 implements Callable<Long> {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Long call() throws Exception {
        Integer speed = new Random().nextInt(10) + 1;
        Long distince = 0l;
        for (int i = 1; i <= 100; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            distince = (long)(i * speed);
            System.out.println(this.getName() + "已前进" + (i * speed) + "米");
        }
        return distince;
    }
}
