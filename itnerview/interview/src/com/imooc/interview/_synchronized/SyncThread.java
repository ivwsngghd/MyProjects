package com.imooc.interview._synchronized;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SyncThread implements Runnable {

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        if (threadName.startsWith("A")) {
            async();
        } else if (threadName.startsWith("B")) {
            syncObjectBlock1();
        } else if (threadName.startsWith("C")) {
            syncObjectMethod1();
        } else if (threadName.startsWith("D")) {
            syncClassBlock1();
        } else if (threadName.startsWith("E")) {
            syncClassMethod1();
        }

    }

    /**
     * 异步方法
     * A
     */
    private void async() {
        try {
            Thread.sleep(500);
            System.out.println("运行的是：" + Thread.currentThread().getName() + "_Async_Start: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + "_Async_End: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 方法中有 synchronized(this|object) {} 同步代码块
     * B
     * 种类：对象锁
     */
    private void syncObjectBlock1() {
        System.out.println("运行的是：" + Thread.currentThread().getName() + "_SyncObjectBlock1: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        synchronized (this) {
            try {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + "_SyncObjectBlock1_Start: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + "_SyncObjectBlock1_End: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * synchronized 修饰非静态方法
     * C
     * 只针对这个实例化对象
     * 种类：对象锁
     */
    private synchronized void syncObjectMethod1() {
        System.out.println("运行的是：" + Thread.currentThread().getName() + "_SyncObjectMethod1: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + "_SyncObjectMethod1_Start: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + "_SyncObjectMethod1_End: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * synchronized(SyncThread.class){}
     * D
     * 种类：类锁
     * 类锁针对的是不同线程之间
     */
    private void syncClassBlock1() {
        System.out.println("运行的是：" + Thread.currentThread().getName() + "_SyncClassBlock1: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        synchronized (SyncThread.class) {   //锁针对的是不同的临界资源类对象(即不同的临界类对象)，
            try {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + "_SyncClassBlock1_Start: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + "_SyncClassBlock1_End: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * synchronized修饰★静态★方法
     * E
     * 种类：类锁
     * 针对所有操作这个方法的类均要获取锁，针对不同的线程对象；
     */
    private synchronized static void syncClassMethod1() {
        System.out.println("运行的是：" + Thread.currentThread().getName() + "_SyncClassMethod1: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + "_SyncClassMethod1_Start: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + "_SyncClassMethod1_End: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
