package com.spring.boot.demo.service.test;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.spring.boot.demo.mapper.StudentMapper;
import com.spring.boot.demo.models.Student;

public class MybatisTest extends TestBase {

	@Autowired
	private StudentMapper studentMapper;

	@Test
	public void getByIdTest() {
		Student student = studentMapper.getById("3322ec35ec064c3aa6ccba0cbde2da42");

		Assert.assertNotNull(student);
	}

	@Test
	public void listTest() {
		PageHelper.startPage(1, 10);
		PageHelper.orderBy("id");
		List<Student> students = studentMapper.list();
		System.out.println("数量=" + students.size());
		Assert.assertNotNull(students);
	}

	@Test
	public void insertTest() {

		Student student = new Student();
		student.setBirthday(new Date());
		student.setEmail("1366678737@qq.com");
		student.setId(UUID.randomUUID().toString());
		student.setName("insert");
		int result = studentMapper.insert(student);

		Assert.assertTrue(result == 1);
	}
}
