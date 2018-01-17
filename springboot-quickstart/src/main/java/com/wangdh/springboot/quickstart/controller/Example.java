package com.wangdh.springboot.quickstart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangdh
 * @Description
 * @date 2018-01-17
 */
@RestController
public class Example {
    @RequestMapping("/")
    public  String home(){
        return "Hello Spring Boot 22333";
    }
}
