package com.spring.boot.demo.multidatasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源,获取key
 * @author wdhcxx
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource{
	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.getType();
	}
}