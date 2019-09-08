package com.imooc.interview.socket.TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
    //和本线程相关的Socket
    Socket socket = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        System.out.println("与客户端建立连接……");
        InputStreamReader isr = null; //获取字节流并转换为字符流
        BufferedReader br = null;
        PrintWriter pw = null;     //获取输出流;

        try {
            isr = new InputStreamReader(socket.getInputStream());
            br = new BufferedReader(isr);

            String info = null;
            while ((info = br.readLine()) != null) {
                System.out.println("我是服务器，客户端说:" + info);
            }
            socket.shutdownInput(); //关闭TCP连接，断开通信 停止读取

            pw = new PrintWriter(socket.getOutputStream());
            pw.write("欢迎您！");
            pw.flush();
            socket.shutdownOutput();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭资源
            try {
                br.close();
                isr.close();
                socket.close();
                pw.close();
            } catch (IOException e) {

            }
        }
    }

}
