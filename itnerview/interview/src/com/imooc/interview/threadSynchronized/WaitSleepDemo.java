package com.imooc.interview.threadSynchronized;

public class WaitSleepDemo {
    public static void main(String[] args) {
        final Object lock = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadSynchronized A is waiting to get lock");
                synchronized (lock){
                    try {
                        System.out.println("threadSynchronized A get lock");
                        Thread.sleep(20);
                        System.out.println("threadSynchronized A do wait method");
                        lock.wait();
                        System.out.println("threadSynchronized A is done");
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        try{
            Thread.sleep(10);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadSynchronized B is waiting to get lock");
                synchronized (lock){
                    try {
                        System.out.println("threadSynchronized B get lock");
                        System.out.println("threadSynchronized B is sleeping 10 ms");
                        Thread.sleep(10);
                        lock.notifyAll();
                        Thread.yield();
                        Thread.sleep(2000);
                        System.out.println("threadSynchronized B is done");
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
