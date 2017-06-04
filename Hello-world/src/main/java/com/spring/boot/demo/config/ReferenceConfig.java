package com.spring.boot.demo.config;

import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.spring.boot.demo.models.Person;

/**
 * 消费者配置
 * @author wdhcxx
 *
 */
@Configuration
public class ReferenceConfig extends DubboBaseConfig{
	// @Bean
    public ReferenceBean<Person> person() {
        ReferenceBean<Person> ref = new ReferenceBean<>();
        ref.setVersion("myversion");
        ref.setInterface(Person.class);
        ref.setTimeout(5000);
        ref.setRetries(3);
        ref.setCheck(false);
        
        return ref;
    }
}
