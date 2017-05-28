package com.spring.boot.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@RequestMapping("/")  
	  public String hello(){  
	    return "Hello world!我修改了";  
	  } 
	
	
}
