package com.imooc.interview.socket.TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {
        try {
            //1.创建一个服务器端socket,指定绑定的的端口，并监听端口
            ServerSocket serverSocket = new ServerSocket(8888); //用于监听，并获取Socket操作对象
            Socket socket = null;
            int count = 0;


            System.out.println("服务器启动，的等待客户端的连接……");
            while(true){
                socket = serverSocket.accept(); //开始阻塞线程并监听
                ServerThread serverThread = new ServerThread(socket); //监听到连接，就创建一个socket，并且分配一个线程
                serverThread.start();   //线程启动，处理事务
                count ++;   //统计客户端数量
                System.out.println("已服务完的客户端的数量：" + count);

                InetAddress address = socket.getInetAddress();
                System.out.println("当前客户端的IP:" + address.getHostAddress());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
