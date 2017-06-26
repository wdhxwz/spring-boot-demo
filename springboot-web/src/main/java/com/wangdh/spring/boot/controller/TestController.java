package com.wangdh.spring.boot.controller;

import java.util.UUID;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wangdh.spring.boot.config.bean.Author;
import com.wangdh.spring.boot.config.bean.Company;

@RestController
@RequestMapping("/test")
public class TestController {
	private static Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	RedissonClient redissonClient;

	@Autowired
	Author author;

	@Autowired
	Company company;

	@RequestMapping("/sayhello")
	public String sayHello() {
		logger.info("请求开始...");

		RBucket<String> rBucket = redissonClient.getBucket("test:spring-boot:sayhello");
		rBucket.set(UUID.randomUUID().toString());

		System.out.println(company.getCompany() + "," + company.getYear() + "," + company.getAddress());

		return "hello " + author.getName();
	}
}