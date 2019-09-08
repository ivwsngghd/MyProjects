package com.imooc.spring.instance;

/**
 * Person静态工厂
 */
public class PersonStaticFactory {
    public static Person createPerson(String name,int age){

        return new Person(name,age);
    }
}
