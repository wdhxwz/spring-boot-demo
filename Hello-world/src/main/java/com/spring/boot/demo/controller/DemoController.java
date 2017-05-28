package com.spring.boot.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.spring.boot.demo.models.Person;
import com.spring.boot.demo.models.Student;
import com.spring.boot.demo.service.StudentService;

@RestController
@RequestMapping("/demo")
public class DemoController {

	@Autowired
	private StudentService studentService;

	@RequestMapping("/getPerson")
	public Person getPerson() {
		Person person = new Person();
		person.setAge(25);
		person.setId(1);
		person.setName("demo");

		return person;
	}

	@RequestMapping("/getStudent")
	public Student getStudent(String id) {
		return studentService.get(id);
	}

	@RequestMapping("/getBean")
	public Student getBean(HttpServletRequest request) {
		Student student = null;
		
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		if (context != null) {
			StudentService ss =context.getBean(StudentService.class);
			student = ss.get("1cbf4759fa4e499db7b8ec30444b00fe");
		}
		
		return student;
	}

	@RequestMapping("/zeroException")
	public int zeroException() {
		return 100 / 0;
	}
}
