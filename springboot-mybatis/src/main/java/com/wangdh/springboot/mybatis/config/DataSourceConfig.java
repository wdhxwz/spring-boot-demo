package com.wangdh.springboot.mybatis.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DataSourceConfig {

	@Bean
	public DataSource dataSource(DruidSource druidSource){
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUsername(druidSource.getUserName());
		druidDataSource.setPassword(druidSource.getPassword());
		druidDataSource.setDriverClassName(druidSource.getDriverClassName());
		druidDataSource.setUrl(druidSource.getUrl());
		druidDataSource.setValidationQuery(druidSource.getValidationQuery());
		
		return druidDataSource;
	}
	
	@Bean
    public DataSourceTransactionManager txManager(DataSource dataSource) {
    	return new DataSourceTransactionManager(dataSource);
    }
}