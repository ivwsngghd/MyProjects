package com.imooc.interview._关于多线程的问题._synchronized锁的各种使用方式;

public class SynchronizedObkectCodeBlock implements Runnable{
    private static SynchronizedObkectCodeBlock synchronizedObkectCodeBlock = new SynchronizedObkectCodeBlock();
    Object lock1 = new Object();
    Object lock2 = new Object();

    @Override
    public void run() {
        synchronized (lock1){
            System.out.println("我是lock1。我叫" + Thread.currentThread().getName());
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " lock1部分运行结束");
        }
        synchronized (lock2){
            System.out.println("我是lock2。我叫" + Thread.currentThread().getName());
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " lock2部分运行结束");
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(synchronizedObkectCodeBlock);
        Thread thread2 = new Thread(synchronizedObkectCodeBlock);
        thread1.start();
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()){

        }
        System.out.println("thread finished");

    }
}
