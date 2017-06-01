package com.spring.boot.demo.service.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.boot.demo.service.HelloService;
import com.wangdh.learner.dubbo.api.ISampleService;

public class HelloServiceTest extends TestBase {
	@Autowired
	private HelloService helloService;
	
	@Autowired
	private ISampleService sampleService;
	
	@Test
	public void sayHelloTest(){
		Assert.assertEquals("hello world", helloService.sayHello());
	}
	
	@Test
	public void dubboTest(){
		String response = sampleService.sayHello("spring boot");
		System.out.println(response);
		Assert.assertNotNull(response);
	}
}