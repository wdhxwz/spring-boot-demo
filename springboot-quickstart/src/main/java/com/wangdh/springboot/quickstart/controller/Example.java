package com.wangdh.springboot.quickstart.controller;

import com.wangdh.springboot.quickstart.models.AppInfo;
import com.wangdh.springboot.quickstart.models.Company;
import com.wangdh.springboot.quickstart.models.MyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
        return "Hello "
                + myBean.getName() +"\n"
                + myBean.getAppName() + "\n"
                + myBean.getAppDescription()+ "\n"
                + myBean.getDb()+ "\n"
                + myBean.getMq()+ "\n"
                + myBean.getCompany()+ "\n";
    }

    @RequestMapping("/json")
    public Object json(){
        Map<String,String> map = new HashMap<String,String>(4);
        map.put("name","wangdh");
        map.put("age","26-2");

        return map;
    }

    @Autowired
    private AppInfo appInfo;
    @RequestMapping("/appInfo")
    public Object getAppInfo(){
        return  appInfo;
    }

    @Autowired
    private Company company;
    @RequestMapping("/getCompany")
    public Company getCompany(){
        return  company;
    }
}
