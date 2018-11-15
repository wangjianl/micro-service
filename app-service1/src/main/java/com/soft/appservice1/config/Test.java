package com.soft.appservice1.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author ptmind
 *
 * 测试Configuration 核心配置注册类
 * @Create 2018-11-15
 */
public class Test {

    public static void main(String[] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfigTest.class);
        JavaConfig javaConfig = (JavaConfig) context.getBean("JavaConfigBean");
        javaConfig.testJavaConfig();
    }
}
