package com.spring.boot.demo.utils;

import java.util.List;

import org.springframework.util.StringUtils;

public class JsonUtils {

	private static JsonMapper jsonMapper = JsonMapper.getDefault();

	public static String toJson(Object object) {
		if (object == null)
			return "{}";
		return jsonMapper.toJson(object);
	}

	/**
	 * 不含值为null的属性
	 * 
	 * @param object
	 * @return
	 */
	public static String toJsonIgnoreNullField(Object object) {
		return JsonMapper.nonNullMapper().toJson(object);
	}

	public static <T> T toObject(String jsonString, Class<T> clazz) {
		return jsonMapper.toObject(jsonString, clazz);
	}

	public static <T> List<T> toList(String jsonString, Class<T> clazz) {
		return jsonMapper.toList(jsonString, clazz);
	}

	/**
	 * 获取json一个更节点的值，如果出现多个key，返回最后一个<br/>
	 * 该方法存在bug，会带双引号，请使用{@linkplain com.frontpay.base.utils.json.JsonUtils.getNode
	 * getNode}方法
	 * 
	 * @param jsonString
	 * @param key
	 * @return
	 */
	@Deprecated
	public static String getString(String jsonString, String key) {
		if (jsonString == null || jsonString.trim().equals(""))
			return null;
		return jsonMapper.getBykey(jsonString, key);
	}

	/**
	 * 获取json一个更节点的值，如果出现多个key，返回最后一个
	 * 
	 * @param jsonString
	 * @param key
	 * @return
	 */
	public static String getNode(String jsonString, String key) {
		if (jsonString == null || jsonString.trim().equals(""))
			return null;
		String val = jsonMapper.getBykey(jsonString, key);
		if (StringUtils.hasText(val)) {
			return null;
		}
		if ("null".equals(val.trim()))
			val = null;
		else if (val.indexOf("\"") == 0 && val.lastIndexOf("\"") + 1 == val.length()) {// 首位都用双引号"
			val = val.substring(0, val.lastIndexOf("\""));// 先去掉尾部的双引号
			val = val.replace("\"", "");// 再去掉头部双引号
		}

		return val;
	}

}