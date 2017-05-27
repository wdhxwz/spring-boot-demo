package com.spring.boot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
	/**
	 * 程序扫描的包默认是执行类所在包及其子包
	 */
	public static void main(String[] args) {		
		 SpringApplication.run(App.class, args);  
	}
}