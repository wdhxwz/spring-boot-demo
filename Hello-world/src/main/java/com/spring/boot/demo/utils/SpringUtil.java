package com.spring.boot.demo.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationObjectSupport;

@Component
public class SpringUtil extends WebApplicationObjectSupport {

	private static ApplicationContext context = null;

	@Override
	protected void initApplicationContext(ApplicationContext context) throws BeansException {
		super.initApplicationContext(context);
		if (SpringUtil.context == null) {
			SpringUtil.context = context;
		}
	}
	
	public static ApplicationContext getContext(){
		return context;
	}
	
	public static <T> T getBean(Class<T> requiredType){
		return getContext().getBean(requiredType);
	}
}