package com.wangdh.springboot.mybatis.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.wangdh.mybatis.mapper.UserEntityMapper;
import com.wangdh.mybatis.mapper.entity.UserEntity;

@RestController
@RequestMapping
public class HomeController {
	@Autowired
	private UserEntityMapper userMapper;
	
	@RequestMapping("/")
	public String index(){
		PageHelper.startPage(1, 2); // 分页查询
		List<UserEntity> users =  userMapper.selectAll();
		for (UserEntity userEntity : users) {
			System.out.println(userEntity.getId());
		}
		
		return "hello spring boot mybatis";
	}
}
