package com.spring.boot.demo.apirouter;

import java.io.Serializable;

/**
 * 响应结果
 * @author wdhcxx
 *
 */
public class ServiceResult implements Serializable{
	private static final long serialVersionUID = -5422771226114393067L;

	/**
	 * 响应码
	 */
	private String code;
	
	/**
	 * 响应消息
	 */
	private String message;
	
	/**
	 * 响应数据
	 */
	private Object data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}