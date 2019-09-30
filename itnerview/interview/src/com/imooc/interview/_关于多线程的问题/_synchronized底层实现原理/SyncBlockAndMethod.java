package com.imooc.interview._关于多线程的问题._synchronized底层实现原理;

public class SyncBlockAndMethod {
    public void syncsTask() {
        //同步代码库
        synchronized (this) {
            System.out.println("Hello");
            synchronized (this){            //可重入性
                System.out.println("World");
            }
        }
    }

    public synchronized void syncTask() {
        System.out.println("Hello Again");
    }

}
/*
* monitor 管程设计
*
* */