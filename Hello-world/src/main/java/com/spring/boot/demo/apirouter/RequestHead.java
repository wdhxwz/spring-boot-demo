package com.spring.boot.demo.apirouter;

import java.io.Serializable;

/**
 * 接口请求头
 * @author wdhcxx
 *
 */
public class RequestHead implements Serializable{
	private static final long serialVersionUID = 8723486863555498187L;
	
	/**
	 *  接口版本号
	 */
	private String version;
	
	/**
	 * 应用编号
	 */
	private String appKey;
	
	/**
	 *  接口编号
	 */
	private String apiNo;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getApiNo() {
		return apiNo;
	}

	public void setApiNo(String apiNo) {
		this.apiNo = apiNo;
	}
}