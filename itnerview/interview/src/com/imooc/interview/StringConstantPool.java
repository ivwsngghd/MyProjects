package com.imooc.interview;

public class StringConstantPool {
    public static void main(String[] args) {
        String s3 = "a" + "h" + new String("h");
        String standard = "ahh";
        String s1 = "a" + "hh";
        String s2 = "a" + "h" + "h";
        String s4 = ("a" + new String("hh")).intern();  //手动入池
        //有new(即引用) 则存放在堆中，否则均在常量池中
        // 如果涉及引用(如new的匿名对象引用) 调用intern()方法 ,可进行入池操作
        //例子如下
        System.out.println("s1==s2：" + (s1 == s2)); //字符串拼接均为常量，true
        System.out.println("s1==s3：" +(s1 == s3)); //new String("h")实则为引用，因此返回引用，实例存放于堆中；返回false
        System.out.println("s1==s4：" +(s1 == s4)); //对比的目标入池了，因此true

        System.out.println("standard == s1：" + (standard == s1));   //字符串拼接均为常量，true
        System.out.println("standard == s2：" + (standard == s2));   //同理
        System.out.println("standard == s3：" + (standard == s3));   //涉及引用 false;
        System.out.println("standard == s4：" + (standard == s4));   //虽然涉及引用，但是手动入池了，堆区转为方法区(常量池)存放；

        //典型例子：
        String str1 = new String("a") + new String("a"); //new 在堆中进行运算，那么只是单纯把引用加入了常量池而已，地址是在堆中的
        str1.intern();          //入池则为true;无入池，就存放在堆区了，这也就是为什么new的不一样
        String str2 = "aa";//直接在常量池生成
        System.out.println("str1 == str2:  " + (str1 == str2));

        //总结：""的形式的字符串在编译阶段会被编译器进行优化(入池)，而设计引用形式的，编译器无法进行优化，会在堆区进行操作；
        //""的复制因为不可变的特性，只复制引用对象(指针)；效率高；
        //不可变，线程安全
        //缺点：涉及字符串操作的时候消耗大

        /*
        *   new() 在堆中生成，而
        *   当调用intern()方法时，如果字符串常量池，先前已创建出该字符串对象，则返回池中的该字符串的引用；(重点！入池会失败！！因此比较会依然不同)
        *   JDK6+才有这个：否则，若该字符串对象已经存在于Java堆中，则将堆中对此对象的引用添加到字符串常量池中，并返回该引用；
        *   如果堆中不存在，则在池中创建该字符串并返回其引用；
        *
        *   避免了字符串把JVM的永久代内存耗尽
        * */

    }
}
