package com.imooc.interview.socket.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        //1.创建服务器端DatagramSocket 指定端口
        DatagramSocket socket = new DatagramSocket(2333);

        //2.创建数据包，用于接收客户端发送的数据
        byte[] data = new byte[1024];//创建字节数组，指定接收的数据包的大小
        DatagramPacket packet = new DatagramPacket(data,data.length);

        //3.接收客户端发送的数据
        System.out.println("服务器端已经启动，等待客户端发送数据……");
        socket.receive(packet); //此方法在接收到数据包之前会一直阻塞；
        //接收到信息后，packet会赋值目标InetAddress属性，并且把收到的信息存放在里面；

        System.out.println(Arrays.toString(data));
        //4.读取数据
        String info = new String(data,0,packet.getLength());
        System.out.println("我是服务器，客户端说：" + info);


        /*
        * 向客户端响应数据
        * */

        //定义客户端的地址、端口号、数据
        InetAddress address = packet.getAddress();

        int port = packet.getPort();
        byte[] data2 = "欢迎您！".getBytes();
        //2.创建数据报，包含响应的数据信息
        DatagramPacket packet1 = new DatagramPacket(data2,data2.length,address,port);

        //3.响应客户端：
        socket.send(packet1);
        socket.close();

    }
}
