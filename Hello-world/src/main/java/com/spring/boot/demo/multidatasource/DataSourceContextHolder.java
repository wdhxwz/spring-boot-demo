package com.spring.boot.demo.multidatasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据源上下文，保存数据库标志
 * 
 * @author wdhcxx
 *
 */
public class DataSourceContextHolder {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceContextHolder.class);
	public static final String DATA_SOURCE_WRITE = "WRITE";
	public static final String DATA_SOURCE_READ = "READ";

	// 线程本地环境
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	// 设置数据源类型
	public static void setType(String type) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("==============切换数据源，类型：" + type + "================");
		}

		contextHolder.set(type);
	}

	// 获取数据源类型
	public static String getType() {
		return (contextHolder.get());
	}

	// 清除数据源类型
	public static void clearType() {
		contextHolder.remove();
	}
}