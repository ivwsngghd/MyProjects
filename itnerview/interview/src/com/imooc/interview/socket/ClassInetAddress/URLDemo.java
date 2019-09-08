package com.imooc.interview.socket.ClassInetAddress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLDemo {
    public static void main(String[] args) {
        try {
//            URL bilibili = new URL("https://www.bilibili.com");
//            URL url = new URL("http://www.bilibili.com");
            URL url = new URL("http://www.bilibili.com/index.html");
//            URL url = new URL(bilibili,"/index.html?username=tom#test");//"？"后面表示的是参数，#表示的是，锚点
            System.out.println("协议：" + url.getProtocol());
            System.out.println("主机：" + url.getHost());
            System.out.println("端口：" + url.getPort());  //若没有指定端口，使用默认端口，返回-1表示使用默认端口

            System.out.println("文件路径：" + url.getPath());
            System.out.println("文件名：" + url.getFile());
            System.out.println("相对路径：" + url.getRef());
            System.out.println("查询字符串：" + url.getQuery());

            InputStream is = url.openStream();

            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bfr = new BufferedReader(isr);

            String data = bfr.readLine();
            while (data!=null){
                System.out.println(data);
                data=bfr.readLine();
            }

            bfr.close();
            isr.close();
            is.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
