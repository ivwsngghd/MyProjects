package com.imooc.spring.scope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ScopeRunner {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:scope.xml");
        UserService userService1 = (UserService)ctx.getBean("userService");
        UserService userService2 = (UserService)ctx.getBean("userService");
    }
}
