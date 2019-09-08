package com.imooc.spring.instance;

/**
 * Person实例工厂
 */
public class PersonFactory {
    public Person createPerson(String name,int age){
        return new Person(name,age);
    }
}
