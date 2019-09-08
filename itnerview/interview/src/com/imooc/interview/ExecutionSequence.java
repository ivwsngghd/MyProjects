package com.imooc.interview;
//请写出程序输出结果
//1. 静态优先
//2. 父类优先
//3. 非静态块优先于构造函数
public class ExecutionSequence {
    public static void main(String[] args) {
        new GeneralClass();
    }
}
class ParentClass{
    static {
        System.out.println("①我是父类静态块");
    }

    {
        System.out.println("②我是父类非静态块");
    }
    public ParentClass(){
        System.out.println("③我是父类构造函数");
    }
}

class GeneralClass extends ParentClass{
    static{
        System.out.println("④我是子类静态块");
    }

    {
        System.out.println("⑤我是子类非静态块");
    }

    public GeneralClass(){
        System.out.println("⑥我是子类构造函数");
    }
}