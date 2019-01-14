package com.soft.scheduler.controller;
/**
 * Created by wangjian on 19/1/13.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 */

@RestController
public class HelloTestController {

    Logger logger = LoggerFactory.getLogger(HelloTestController.class);

    @GetMapping("/hello")
    public String hello(){
        return "hello test";
    }
}
