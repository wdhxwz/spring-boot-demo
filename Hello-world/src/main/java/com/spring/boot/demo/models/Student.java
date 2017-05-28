package com.spring.boot.demo.models;

import java.util.Date;

public class Student extends BaseModel<String>{
	private static final long serialVersionUID = 8162809836585479791L;
	
	private String name;
	private String email;
	private Date birthday;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}	
}