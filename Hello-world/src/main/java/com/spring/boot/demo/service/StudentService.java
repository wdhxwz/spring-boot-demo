package com.spring.boot.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.spring.boot.demo.dao.StudentDao;
import com.spring.boot.demo.models.Student;

@Service
public class StudentService {

	@Autowired
	private StudentDao studentDao;

	@Cacheable(value = "student",key="'student.'+#id")
	public Student get(String id) {
		System.out.println("从数据库获取,id=" + id);

		return studentDao.get(id);
	}
}