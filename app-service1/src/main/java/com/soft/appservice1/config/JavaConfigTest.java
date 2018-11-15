package com.soft.appservice1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author ptmind
 * @Create 2018-11-15
 */

@Configuration
public class JavaConfigTest {

    @Bean(name="JavaConfigBean")
    public JavaConfig beanTest(){
        return new JavaConfigImpl();
    }
}
