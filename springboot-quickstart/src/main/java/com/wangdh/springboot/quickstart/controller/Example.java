package com.wangdh.springboot.quickstart.controller;

import com.wangdh.springboot.quickstart.models.MyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangdh
 * @Description
 * @date 2018-01-17
 */
@RestController
public class Example {
    @Autowired
    MyBean myBean;

    @RequestMapping("/")
    public  String home(){
        return "Hello " + myBean.getName() +"\n" + myBean.getAppName() + "\n" + myBean.getAppDescription();
    }
}
