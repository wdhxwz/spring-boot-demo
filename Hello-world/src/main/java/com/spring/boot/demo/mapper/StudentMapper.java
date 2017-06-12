package com.spring.boot.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.spring.boot.demo.models.Student;

@Component
public interface StudentMapper {
	@Select("SELECT [Id],[name],[email],[birthday] FROM [MybatisDB].[dbo].[tb_student] WHERE Id = #{id}")
	public Student getById(String id);
	
	@Select("SELECT [Id],[name],[email],[birthday] FROM [MybatisDB].[dbo].[tb_student]")
	public List<Student> list();
	
	@Insert("INSERT INTO [MybatisDB].[dbo].[tb_student]([Id],[name],[email],[birthday]) VALUES (#{id},#{name},#{email},#{birthday})")
	public int insert(Student student);
}
