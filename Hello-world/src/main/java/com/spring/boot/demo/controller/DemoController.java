package com.spring.boot.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.demo.models.Person;

@RestController
@RequestMapping("/demo")
public class DemoController {

	@RequestMapping("/getPerson")
	public Person getPerson() {
		Person person = new Person();
		person.setAge(25);
		person.setId(1);
		person.setName("demo");

		return person;
	}
	
	@RequestMapping("/zeroException")
    public int zeroException(){
       return 100/0;
    }
}
