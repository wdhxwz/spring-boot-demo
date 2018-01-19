package com.wangdh.springboot.quickstart.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author wangdh
 * @Description
 * @date 2018-01-18
 */
@Component
public class MyBean {
    @Value("${name}")
    private String name;

    @Value("${app.name}")
    private String appName;

    @Value("${app.description}")
    private String appDescription;

    @Value("${db}")
    private String db;

    @Value("${mq}")
    private String mq;

    public String getName() {
        return name;
    }

    public String getAppName(){
        return  appName;
    }

    public  String getAppDescription(){
        return appDescription;
    }

    public String getDb(){
        return db;
    }

    public String getMq(){
        return mq;
    }
}
