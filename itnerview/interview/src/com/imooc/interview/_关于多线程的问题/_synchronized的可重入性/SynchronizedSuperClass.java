package com.imooc.interview._关于多线程的问题._synchronized的可重入性;

public class SynchronizedSuperClass {
    public synchronized void doSomething(){
        System.out.println("我是父类方法");
    }

    public static void main(String[] args) {
        new testClass().doSomething();
    }

}

class testClass extends SynchronizedSuperClass{
    @Override
    public synchronized void doSomething(){
        System.out.println("我是子类方法");
        super.doSomething();
    }
}


