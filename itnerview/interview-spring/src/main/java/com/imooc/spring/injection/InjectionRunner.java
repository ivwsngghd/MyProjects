package com.imooc.spring.injection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InjectionRunner {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:injection.xml");
        UserService userService = (UserService)ctx.getBean("userService");
        userService.createUser();
    }
}
