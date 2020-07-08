package com.huamile.test;

import com.huamile.service.CustomerService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Afa
 */
public class TestSpring {
    @Test
    public void run1(){
        //加载配置文件
        ApplicationContext as = new ClassPathXmlApplicationContext("classpath:spring.xml");
        //获取对象
        CustomerService csi = as.getBean("customerServiceImpl", CustomerService.class);
        //调用方法
        csi.countByExample(null);
    }
}
