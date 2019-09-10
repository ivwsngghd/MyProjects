package Java的一些特殊的语言特性;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class _线程池的种类和创建方法以及Socket的基本使用方法 {
    public static void main(String[] args) throws IOException {
        NetworkService networkService = new NetworkService(8888, 10);

        new Thread(networkService).start();
    }
}

class NetworkService implements Runnable {
    private final ServerSocket serverSocket;
    private final ExecutorService pool;

    public NetworkService(int port, int poolSize) throws IOException {
        serverSocket = new ServerSocket(port);
        // ★★★★★★★★★★★★★★★★★★★
//        pool = Executors.newFixedThreadPool(poolSize);  //线程池的使用方法，针对不同的应用场景有不同的功能线程池
//        pool = Executors.newFixedThreadPool(int poolSize);  //指定线程数量的线程池
//        pool = Executors.newCachedThreadPool();  //处理大量短时间进程；试图缓存线程并重用；有时间阈值，超过会把线程移出缓存；长时间闲置只占用少量系统资源
//        pool = Executors.newSingleThreadExecutor();  //只有一个工作线程对任务进行操作，如果出现异常，会有另一个线程取代它
//        pool = Executors.newSingleThreadScheduledExecutor(int poolSize);  //定时或周期性的工作调度，与前者的区别在于单一工作线程还是多个线程
        pool = Executors.newWorkStealingPool();  //内部构建ForkJoinPool;利用working-stealing算法，并行地处理任务，不保证处理顺序；

    }

    public void run() { // run the service
        try {
            System.out.println("服务端已经启动");
            for (; ; ) {
                pool.execute(new Handler(serverSocket.accept()));
            }
        } catch (IOException ex) {
//            serverSocket.close();
            pool.shutdown();
        }
    }
}

class Handler implements Runnable {
    private final Socket socket;

    Handler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        // read and service request on socket
        BufferedReader bufferedReader = null;

        try {
            // 复习点：注意InputStreamReader是用于把字节流转换成字符流，而且每次读取返回int数据(一个字符大小)，
            // 而且根据编码类型来获取对象，譬如AscⅡ编码，获得的65，转为char就是A
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String str;
            while((str = bufferedReader.readLine())!=null){
                System.out.println("客户端说：" + str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



/*
 * Fork/Join 原理和MapReduce一样
 * work-stealing算法：某个线程从其他队列里窃取任务来执行
 * 把大任务分割成若干个小人物并行执行，最后汇总；
 * 一般从后、前分别获取任务，双端队列
 * */


/* J.U.C  ===  java.util.concurrent
* 为什么要使用线程池？
* 降低资源消耗
* 提高线程的可管理性
*
* */

/* J.U.C的三个Executor接口
* Executor：(没有submit方法)运行新任务的简单接口，将任务提交和执行的细节解耦
* ExecutorService：具有Submit方法（使用submit方法和execute方法）
 *       -   submit方法有返回值，execute没有；
 *       -   submit方便Exception处理：可处理非RuntimeException
 * ScheduledExecutorService：支持Future和定期执行的任务
* */

/*
* 线程池框架：
* 最基础的是ThreadPoolExecutor(大多均继承该类，自己如果对线程池有更多的需求，则自己继承和编写)
* 构造函数参数列表：
*       - corePoolSize：核心线程数量
*       - maximumPoolSize：线程不够用时能够创建的最大线程数
*       - workQueue：任务等待队列
*       - keepAliveTime：抢占的顺序不一定，看运气
*       - threadFactory：创建新线程，Executors.defaultThreadFactory();
*       - handler：线程的饱和策略
*           · AbortPolicy：直接抛出异常，这是默认策略
*           · CallerRunsPolicy：调用者所在的线程来执行任务
*           · DiscardOldestPolicy：丢弃队列中最靠前的任务，并执行当前任务
*           · DiscardPolicy：直接丢弃任务
*           · 实现RejectedExecutionHandler接口自定义的Handler
*
*       - 判断线程数和当前允许工作的workQueue的大小maximumPoolSize，总和来判断是否执行相应策略
*           - 如新建线程加入队列、阻塞后加入队列，不允许增加直接执行handler等
*
*       - AtomicInteger 用于表示当前线程(池)状态(有对应的值)的一个数字(数据集合)
*       - 可以类比PCB里对进程的状态管理信息
*       - 运行状态、有效线程数量、获取运行状态，线程数
*       - 线程池的状态：
*           · RUNNING 可接受新递交的任务，并且能处理阻塞队列中的任务
*           · SHUTDOWN 不再接受新提交的任务，但可以处理存量任务(已经加入阻塞队列但未处理)
*           · STOP 不再接受新提交的任务，也不处理存量任务(直接中断线程)
*           · TIDYING 所有的任务都已终止
*           · TERMINATED terminated()方法执行完后进入该状态 (终结)
*
*       - 线程池大小的选定：
*       - CPU多：线程数=核数+1
*       - I/O密集型：线程数=CPU*核数*(1+平均等待时间/平均工作时间)
*
* */