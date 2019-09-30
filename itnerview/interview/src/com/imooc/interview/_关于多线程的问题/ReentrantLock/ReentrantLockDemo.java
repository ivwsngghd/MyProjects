package com.imooc.interview._关于多线程的问题.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo implements Runnable {
    private static ReentrantLock reentrantLock = new ReentrantLock(false);   //fair是否公平，即偏向等待时间长的线程

    @Override
    public void run() {
        while (true) {   //增强
            try {
                reentrantLock.lock();    //获取锁 如果没有
                System.out.println(Thread.currentThread().getName() + " get reentrantLock");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock(); //释放锁
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo rtld = new ReentrantLockDemo();
        Thread thread1 = new Thread(rtld);
        Thread thread2 = new Thread(rtld);
        thread1.start();
        thread2.start();
    }
}


