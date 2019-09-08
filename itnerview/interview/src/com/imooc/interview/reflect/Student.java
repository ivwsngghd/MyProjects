package com.imooc.interview.reflect;

public class Student {
    private Integer id;
    private String name;
    private Integer age;
    private String sex;

    public Student() {

    }

    private String sayShit(String str, Integer num, int num1) {
        System.out.println("学生说：Shit!");
        System.out.println("传入的String参数1：" + str + "\n传入的Integer参数2：" + num + "\n传入的int基本数据类型参数3 ：" + num1);

        return "A piece of shit";
    }

    public void sayHello() {
        System.out.println("学生说:Hello!");
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
