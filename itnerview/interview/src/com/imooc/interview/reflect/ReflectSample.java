package com.imooc.interview.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectSample {

    public static void main(String[] args) throws Exception {
        //获取类的属性和方法
        Class studentClass = Class.forName("com.imooc.interview.reflect.Student");

        //获取一个学生类对象
        Student student = (Student) studentClass.newInstance(); //可以实例化构造一个Student类对象

        //获取私有方法的方法如下：          .getMethod()无法获取私有对象
        Method sayShit = studentClass.getDeclaredMethod("sayShit",String.class,Integer.class,int.class);
        sayShit.setAccessible(true);   //默认不可达(false)

        Object str = sayShit.invoke(student,"aaa",123,123);

        System.out.println("调用的私有方法返回的对象为：" + str);

        //获取私有变量属性，无需通过get()  set()方法；
        Field name = studentClass.getDeclaredField("name");
        Field id = studentClass.getDeclaredField("id");
        name.setAccessible(true);
        id.setAccessible(true);

        System.out.println("通过反射获得值的学生名字：" + name.get(student));

        name.set(student,"学生名字：Alice");
        id.set(student,233);

        System.out.println("通过反射直接操作实例化对象,得出的学生名字和id：" + student.getName() +"   "+ "学生ID：" +student.getId());
        System.out.println("通过反射获得值的学生名字：" + name.get(student));


    }

}