package com.imooc.interview._synchronized;

public class SyncDemo {
    public static void main(String... args) {
        SyncThread syncThread = new SyncThread();
//        Thread A_thread1 = new Thread(syncThread, "A_thread1"); //A类是全异步运行
//        Thread A_thread2 = new Thread(syncThread, "A_thread2");
//        Thread B_thread1 = new Thread(syncThread, "B_thread1"); //B类是同步代码块 synchronized(this){}
//        Thread B_thread2 = new Thread(syncThread, "B_thread2");// B之间不锁类，只锁代码块
//        Thread C_thread1 = new Thread(syncThread, "C_thread1"); //C类是修饰非静态方法  private synchronized void syncObjectMethod1()
//        Thread C_thread2 = new Thread(syncThread, "C_thread2");
        //B和C 使用的是同一把锁

        Thread D_thread1 = new Thread(new SyncThread(), "D_thread1"); //D类是上的类锁
        Thread D_thread2 = new Thread(new SyncThread(), "D_thread2"); //针对临界类对象之间，只有一个线程可以操作这些临界类对象们的这个方法，上面则不受影响，异步运行；
        Thread E_thread1 = new Thread(new SyncThread(), "E_thread1"); //E类是和D类做对比的异步运行线程
        Thread E_thread2 = new Thread(new SyncThread(), "E_thread2");
//        A_thread1.start();
//        A_thread2.start();
//        B_thread1.start();
//        B_thread2.start();
//        C_thread1.start();
//        C_thread2.start();
        D_thread1.start();
        D_thread2.start();
        E_thread1.start();
        E_thread2.start();
    }
}

/*
*   对象锁和类锁的总结
*   1.有线程访问对象的同步代码块时，另外的线程可以访问该对象的非同步代码块；
*   2.若锁住的是同一个对象，一个线程在访问对象的同步★方法★或者★代码块★时，另一个访问对象的同步★方法★和★代码块★的线程均会被阻塞；(使用的是同一把锁)
*   3.同一个类的不同实例对象的对象锁互不干扰；
*   4.类锁是一种特殊的对象锁，他的对象是针对类，因此针对同一个类对象与上述一样；
*       - 而针对★同一个类的多个不同的对象★也是同步的★★★★★ 一个类只有一把类锁
*   5.类锁和对象锁互不干扰 ★
* */