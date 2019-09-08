package com.imooc.interview.socket.ClassInetAddress;

import java.net.InetAddress;
import java.util.Arrays;

public class InetAddressDemo {
    public static void main(String[] args) throws Exception {
        //获取本机的InetAddress实例
        InetAddress address = InetAddress.getLocalHost();
        System.out.println("计算机名字：" + address.getHostName());
        System.out.println("IP地址：" + address.getHostAddress());
        byte[] bytes = address.getAddress(); //获取字节数组形式的IP地址

        System.out.println("字节数组形式的IP：" + Arrays.toString(bytes));
        //byte数组是有符号的。
        // 在Java中byte类型的取值范围是-128〜127，因此获取的值为负数，想要得到正常值，凡是负数的需要加上256

        //还可以通过getByName进行获取：
        InetAddress inetAddress = InetAddress.getByName(address.getHostAddress());
        System.out.println(inetAddress.getHostName());

    }

}
