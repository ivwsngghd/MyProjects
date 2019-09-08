package com.imooc.spring.injection;

import org.springframework.stereotype.Repository;

//@Repository("userDAO")
public class UserDAOImpl implements UserDAO{
    public UserDAOImpl(){
        System.out.println(this + "已创建");
    }
    public void insert() {
        System.out.println(this + ":正在调用UserDAOImpl.insert()");
    }
}
