package com.spring.boot.demo.apiservice;

import com.spring.boot.demo.apirouter.ApiRequest;

/**
 * 获取指定账户id的额度信息
 * @author wdhcxx
 *
 */
public class ApiRequest2002 extends ApiRequest{
	private static final long serialVersionUID = -4917339135986912405L;

	/**
	 * 虚拟账户id
	 */
	private String accountId;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
}