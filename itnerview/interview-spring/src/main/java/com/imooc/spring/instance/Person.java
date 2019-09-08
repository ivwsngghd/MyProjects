package com.imooc.spring.instance;

public class Person {

    private String name;
    private Integer age;


    public Person() {
        System.out.println("Person默认构造函数");
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;

        System.out.println("Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}');
    }
}