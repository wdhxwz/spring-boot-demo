package com.spring.boot.demo.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.spring.boot.demo.multidatasource.DynamicDataSource;

@Configuration
public class MultiDataSourceConfig {
	/**
	 * 读数据源
	 * @return
	 */
	//@Bean(initMethod="init")
	public DataSource readDataSource(){
		DruidDataSource readDataSource = new DruidDataSource();
		readDataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		readDataSource.setPassword("1q2w#E$R");
		readDataSource.setUrl("jdbc:sqlserver://127.0.0.1:1433;databasename=MybatisDB");
		readDataSource.setUsername("sa");
		
		return readDataSource;
	}
	
	/**
	 * 写数据源
	 * @return
	 */
	//@Bean(initMethod="init")
	public DataSource writeDataSource(){
		DruidDataSource readDataSource = new DruidDataSource();
		readDataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		readDataSource.setPassword("1q2w#E$R");
		readDataSource.setUrl("jdbc:sqlserver://127.0.0.1:1433;databasename=MybatisDB");
		readDataSource.setUsername("sa");
		
		return readDataSource;
	}
	
	/**
	 * 动态数据源
	 * @return
	 */
	@Bean
	public DynamicDataSource dataSource(){
		DynamicDataSource dataSource = new DynamicDataSource();
		dataSource.setDefaultTargetDataSource(readDataSource());
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		targetDataSources.put("READ", readDataSource());
		targetDataSources.put("WRITE", writeDataSource());
		dataSource.setTargetDataSources(targetDataSources);
		
		return dataSource;
	}
}