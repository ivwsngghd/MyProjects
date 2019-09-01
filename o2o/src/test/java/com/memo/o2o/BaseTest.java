package com.memo.o2o;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 配合Spring和junit整合，junit启动时加载SpringIOC容器
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"}) //声明junit ,spring配置文件的位置
public class BaseTest {
    //继承的时候会把对应的注解以及属性一同继承
}
