package com.spring.boot.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.demo.dao.StudentDao;
import com.spring.boot.demo.models.Student;

@Service
public class StudentService {

	@Autowired
	private StudentDao studentDao;
	
	
	public Student get(String id){
		return studentDao.get(id);
	}
}