package com.spring.boot.demo.service.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.boot.demo.service.HelloService;

public class HelloServiceTest extends TestBase {
	@Autowired
	private HelloService helloService;
	
	@Test
	public void sayHelloTest(){
		Assert.assertEquals("hello world", helloService.sayHello());
	}
}