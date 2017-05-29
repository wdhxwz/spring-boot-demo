package com.spring.boot.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class MyEnvironmentAware implements EnvironmentAware {

	/**
	 * 注入配置项的值
	 */
	@Value("${myurl}")
	private String myUrl;

	@Override
	public void setEnvironment(Environment environment) {
		System.out.println("myurl=" + myUrl);

		//通过 environment 获取到系统属性.
        System.out.println(environment.getProperty("JAVA_HOME"));
        
        //获取到前缀是"spring.datasource." 的属性列表值.
        RelaxedPropertyResolver relaxedPropertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
        System.out.println("spring.datasource.url="+relaxedPropertyResolver.getProperty("url"));
	}
}
