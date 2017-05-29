package com.spring.boot.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.boot.demo.models.Student;

@Repository
public class StudentDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Student get(String id) {
		String sql = "SELECT [Id],[name],[email],[birthday] FROM [MybatisDB].[dbo].[tb_student] WHERE Id = ?";
		RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);

		Student student = null;
		try {
			// 返回一条结果，相当于select one
			student = jdbcTemplate.queryForObject(sql, rowMapper, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return student;
	}
}