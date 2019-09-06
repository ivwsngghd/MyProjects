package Java的一些特殊的语言特性;

import java.util.Random;
import java.util.concurrent.*;

public class WaysToThreadsImpl {
    public static void main(String[] args) {
        Runner1 r1 = new Runner1();
        r1.setName("张三");
        Runner1 r2 = new Runner1();
        r2.setName("李四");
        Runner1 r3 = new Runner1();
        r3.setName("王五");

//        r1.start();
//        r2.start();
//        r3.start();

        //共5条线程
        // 主线程(守护线程)
        // 3条创建的线程
        // 1个JVM提供的线程运行环境维护的线程


        //方法二
        Thread t1 = new Thread(new Runner2(), "小明");
        Thread t2 = new Thread(new Runner2(), "小红");
        Thread t3 = new Thread(new Runner2(), "小黑");

//        t1.start();
//        t2.start();
//        t3.start();

        ExecutorService threadPool = Executors.newFixedThreadPool(3); //最多三个可同时运行的番线程
        Runner3 c1 = new Runner3();
        c1.setName("弟弟1");
        Runner3 c2 = new Runner3();
        c2.setName("弟弟2");
        Runner3 c3 = new Runner3();
        c3.setName("弟弟3");

        Future<Integer> c1Result = threadPool.submit(c1);
        Future<Integer> c2Result = threadPool.submit(c2);
        Future<Integer> c3Result = threadPool.submit(c3);

        try {
            System.out.println(c1Result.get());
            System.out.println(c2Result.get());
            System.out.println(c3Result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


}

//方法一
class Runner1 extends Thread {
    @Override
    public void run() {
        int speed = new Random().nextInt(10) + 1;
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName() + "已经前进" + (i * speed) + "米");
        }
    }
}

//方法二

class Runner2 implements Runnable {

    @Override
    public void run() {
        int speed = new Random().nextInt(10) + 1;
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "已经前进" + (i * speed) + "米");
        }
    }
}

//方法三 线程池
class Runner3 implements Callable<Integer> {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer call() throws Exception {
        int speed = new Random().nextInt(10) + 1;
        int distinct = 0;
        for (int i = 1; i < 100; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + "已经前进" + (i * speed) + "米");
            distinct = i * speed;
        }
        return distinct;
    }
}

//线程有哪些状态

// 1、新建
// 2、就绪
// 3、运行中
// 4、阻塞
// 5、死亡

