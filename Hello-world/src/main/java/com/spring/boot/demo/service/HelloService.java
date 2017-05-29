package com.spring.boot.demo.service;

public class HelloService {
    public HelloService() {
       System.out.println("HelloService.HelloService()");
       System.out.println("com.spring.boot.demo.service.HelloService()");
       System.out.println("HelloService.HelloService()");
    }
    
    public String sayHello(){
    	return "hello world";
    }
}
