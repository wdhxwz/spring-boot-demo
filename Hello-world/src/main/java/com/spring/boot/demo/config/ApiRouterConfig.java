package com.spring.boot.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.boot.demo.apirouter.ApiRouter;

@Configuration
public class ApiRouterConfig {
	@Bean(initMethod="startup")
	public ApiRouter apiRouter(){
		return new ApiRouter();
	}
}