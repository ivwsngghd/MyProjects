package com.imooc.spring.scope;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class UserService {
    //@Autowired
    //@Resource
    private UserDAO userDAO;

    public UserService() {
        System.out.println(this + "已创建");
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
        System.out.println(this + ":正在调用Setter方法注入，setUserDAO(" + userDAO + ")");
    }

    public void createUser(){
        System.out.println(this + ":正在调用UserService.createUser()");
        userDAO.insert();
    }
}
