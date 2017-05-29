package com.spring.boot.demo.mapper;

import java.util.List;

import com.spring.boot.demo.models.Student;

public interface StudentMapper {
	//@Select("SELECT [Id],[name],[email],[birthday] FROM [MybatisDB].[dbo].[tb_student] WHERE Id = #{id}")
	public Student getById(String id);
	
	//@Select("SELECT [Id],[name],[email],[birthday] FROM [MybatisDB].[dbo].[tb_student]")
	public List<Student> list();
}
