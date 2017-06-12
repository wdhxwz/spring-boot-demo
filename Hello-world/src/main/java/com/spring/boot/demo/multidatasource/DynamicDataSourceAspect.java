package com.spring.boot.demo.multidatasource;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 动态数据源切面类
 * 
 * @author wdhcxx
 *
 */
@Component
@Aspect
public class DynamicDataSourceAspect {
	private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);
	
	@Pointcut("execution (* com.spring.boot.demo.mapper.*.select*(..)) || execution (* com.spring.boot.demo.mapper.*.count*(..)) || execution (* com.spring.boot.demo.mapper.*.get*(..)) || execution (* com.spring.boot.demo.mapper.*.list*(..))")
	public void readMethodPointcut() {
	}

	@Pointcut("execution (* com.spring.boot.demo.mapper.*.insert*(..)) || execution (* com.spring.boot.demo.mapper.*.update*(..)) || execution (* com.spring.boot.demo.mapper.*.delete*(..)) || execution (* com.spring.boot.demo.mapper.*.set*(..))")
	public void writeMethodPointcut() {
	}

	@Before("readMethodPointcut()")
	public void switchReadDataSource() {
		if(logger.isInfoEnabled()){
			logger.info("============切换到读数据源===========");
		}
		DataSourceContextHolder.setType(DataSourceContextHolder.DATA_SOURCE_READ);
	}

	@Before("writeMethodPointcut()")
	public void switchWriteDataSource() {
		if(logger.isInfoEnabled()){
			logger.info("=============切换到写数据源==========");
		}
		DataSourceContextHolder.setType(DataSourceContextHolder.DATA_SOURCE_WRITE);
	}
}