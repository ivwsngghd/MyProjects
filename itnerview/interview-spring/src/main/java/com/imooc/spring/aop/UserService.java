package com.imooc.spring.aop;

import com.imooc.spring.scope.UserDAO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
//@Transactional(propagation = Propagation.REQUIRED , rollbackFor = Exception.class)
@Transactional(propagation = Propagation.NOT_SUPPORTED , readOnly = true )
public class UserService {

    public String createUser(){
        //打开事务
        System.out.println(this + ":正在调用UserService.createUser()");
        //提交事务
        //catch块中回滚事务 - RuntimeException
        return "success";
    }
}
