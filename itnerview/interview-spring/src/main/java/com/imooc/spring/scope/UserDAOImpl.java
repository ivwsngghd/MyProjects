package com.imooc.spring.scope;

//@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
    public UserDAOImpl(){
        System.out.println(this + "已创建");
    }
    public void insert() {
        System.out.println(this + ":正在调用UserDAOImpl.insert()");
    }
}
