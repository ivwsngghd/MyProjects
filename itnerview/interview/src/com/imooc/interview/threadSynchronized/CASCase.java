package com.imooc.interview.threadSynchronized;

public class CASCase {
    public volatile int value;

    public synchronized void add() {
        value++;
    }
}
