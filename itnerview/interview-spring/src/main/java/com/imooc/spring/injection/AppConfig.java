package com.imooc.spring.injection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean(name="userDAO")
    public UserDAO userDAO(){
        return new UserDAOImpl();
    }

    @Bean(name="userService")
    public UserService userService(){
        UserService userService = new UserService();
        userService.setUserDAO(this.userDAO());
        return userService;
    }
}
