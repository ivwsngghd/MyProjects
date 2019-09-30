package com.imooc.interview._关于多线程的问题.ReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _ReentrantLock用法 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();
        lock.tryLock();
//        lock.tryLock(10, TimeUnit.SECONDS);
    }
}
