package com.wangdh.springboot.quickstart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author wangdh
 * @Description
 * @date 2018-01-30
 */
@Controller
@RequestMapping("/plan/")
public class PlanController {

    //private ProjectTypeService projectTypeService;
//    @Resource
//    private WorkProgram workProgram;
    //进入工作计划表主页面
    @RequestMapping("listUI")
    public String listUI() throws Exception {
        System.out.println("进入plan.listUI");

        InputStream inputStream = new FileInputStream("");
        inputStream.read();

        return "table2";
    }
}