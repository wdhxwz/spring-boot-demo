package com.wangdh.spring.boot.config.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置类映射
 * 
 * @author wangdh 2017年6月23日下午5:51:14
 */
@ConfigurationProperties(prefix = "author")
public class Author {
	private String name;
	private String sex;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
