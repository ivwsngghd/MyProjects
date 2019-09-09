package Java的一些特殊的语言特性;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class _线程池的种类和创建方法以及Socket的基本使用方法 {
    public static void main(String[] args) throws IOException {
        NetworkService networkService = new NetworkService(8888, 10);

    }
}

class NetworkService implements Runnable {
    private final ServerSocket serverSocket;
    private final ExecutorService pool;

    public NetworkService(int port, int poolSize) throws IOException {
        serverSocket = new ServerSocket(port);
        pool = Executors.newFixedThreadPool(poolSize);  //线程池的使用方法，针对不同的应用场景有不同的功能线程池

    }

    public void run() { // run the service
        try {
            for (; ; ) {
                pool.execute(new Handler(serverSocket.accept()));
            }
        } catch (IOException ex) {
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
 *
 * */