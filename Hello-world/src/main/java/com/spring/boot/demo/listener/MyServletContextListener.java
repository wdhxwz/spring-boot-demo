package com.spring.boot.demo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("上下文初始化...");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("上下文銷毀...");
	}
}
