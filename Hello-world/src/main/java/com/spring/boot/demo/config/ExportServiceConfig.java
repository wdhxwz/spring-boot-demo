package com.spring.boot.demo.config;

import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.spring.ServiceBean;
import com.spring.boot.demo.models.Person;

/**
 * 生产者配置
 * 
 * @author wdhcxx
 *
 */
@Configuration
public class ExportServiceConfig extends DubboBaseConfig {

	// @Bean
	public ServiceBean<Person> personServiceExport(Person person) {
		ServiceBean<Person> serviceBean = new ServiceBean<Person>();
		serviceBean.setProxy("javassist");
		serviceBean.setVersion("myversion");
		serviceBean.setInterface(Person.class.getName());
		serviceBean.setRef(person);
		serviceBean.setTimeout(5000);
		serviceBean.setRetries(3);
		
		return serviceBean;
	}
}
