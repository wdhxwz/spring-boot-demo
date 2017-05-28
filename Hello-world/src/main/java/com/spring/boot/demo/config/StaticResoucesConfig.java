package com.spring.boot.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 自定义资源映射
 * @author wdhcxx
 *
 */
@Configuration
public class StaticResoucesConfig extends WebMvcConfigurerAdapter{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("classpath:/images/");
		
		super.addResourceHandlers(registry);
	}	
}
