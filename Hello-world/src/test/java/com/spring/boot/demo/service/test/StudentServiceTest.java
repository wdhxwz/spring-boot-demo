package com.spring.boot.demo.service.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.boot.demo.App;
import com.spring.boot.demo.models.Student;
import com.spring.boot.demo.service.StudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=App.class)
public class StudentServiceTest {
	@Autowired
	private StudentService studentService;
	
	@Test
	public void get(){
		Student student = studentService.get("30798c9f2c0b41a6ab36c2a19a6f12b0");
		
		Assert.assertNotNull(student);
	}
}
