package com.spring.boot.demo.apirouter;

import java.io.Serializable;

/**
 * 接口请求抽象类,包含请求头
 * @author wdhcxx
 *
 */
public class ApiRequest implements Serializable{
	private static final long serialVersionUID = -2688553827134549556L;
	
	/**
	 * 请求头
	 */
	private RequestHead requestHead;

	public RequestHead getRequestHead() {
		return requestHead;
	}

	public void setRequestHead(RequestHead requestHead) {
		this.requestHead = requestHead;
	}	
}