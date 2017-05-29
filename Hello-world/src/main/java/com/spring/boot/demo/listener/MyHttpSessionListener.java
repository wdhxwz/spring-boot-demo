package com.spring.boot.demo.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class MyHttpSessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("session 创建后...");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("session 销毁后...");
	}
}
