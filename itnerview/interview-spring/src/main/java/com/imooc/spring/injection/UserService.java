package com.imooc.spring.injection;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

//@Service("userService")
public class UserService {
//    @Resource(name="userDAO")
    private UserDAO userDAO;

    public UserService() {
        System.out.println(this + "已创建");
    }

    //通过构造函数注入
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
        System.out.println(this + ":正在调用构造函数注入，UserService(" + userDAO + ")");
    }

    //通过Setter方法注入
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
        System.out.println(this + ":正在调用Setter方法注入，setUserDAO(" + userDAO + ")");
    }

    public void createUser(){
        System.out.println(this + ":正在调用UserService.createUser()");
        userDAO.insert();
    }
}
