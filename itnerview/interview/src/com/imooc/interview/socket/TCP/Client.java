package com.imooc.interview.socket.TCP;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        //1.创建客户端Socket，指定服务器地址和端口

        try {
            Socket socket = new Socket("localhost", 8888);   //指定服务器地址和端口
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);//获取输出流包装成打印流；
            Scanner in = new Scanner(System.in);
            System.out.println("客户端输入信息：");
            String input ;
            while (!(input = in.next()).equals("-1")){
                System.out.println(input);
                pw.write(input);
                pw.flush();
            }
            socket.shutdownOutput();//关闭输出流 (有真正的flush,发送信息)
            System.out.println("信息已发送");

            //获取输入流，并且包装
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String info;
            while ((info = br.readLine())!= null){
                System.out.println("我是客户端，服务器说：" + info);
            }
            socket.shutdownInput();    //关闭输入流

            //关闭资源
            pw.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
