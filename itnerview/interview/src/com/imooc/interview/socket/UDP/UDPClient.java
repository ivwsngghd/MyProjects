package com.imooc.interview.socket.UDP;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        //1.定义服务器的地址、端口号、数据
        InetAddress address = InetAddress.getByName("localhost");   //获取目的地址的ip信息
        int port = 2333;
        byte[] data = "客户端说，丢雷，炒类啊".getBytes();

        //2.创建数据报，包含发送的数据信息
        DatagramPacket packet = new DatagramPacket(data,data.length,address,port);

        //3.创建DatagramSocket对象
        DatagramSocket socket = new DatagramSocket();

        //4.向服务器端发送数据报
        socket.send(packet);

        /*
        * 接受服务器响应的数据
        * */

        byte[] data2 = new byte[1024];
        DatagramPacket packet1 = new DatagramPacket(data2,data2.length);

        socket.receive(packet1);
        String reply = new String(packet1.getData(),0,packet1.getLength()); //此时 packet1.getData() 返回 data2
        System.out.println("我是客户端，服务端回应说：" + reply);
        socket.close();

    }
}
