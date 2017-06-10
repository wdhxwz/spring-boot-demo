package com.spring.boot.demo.apirouter;

/**
 * 每个服务接口的编号和版本号等信息
 * @author wdhcxx
 *
 */
public class ServiceMethodDefinition {

	/**
     * 对应的版本号，如果为null或""表示不区分版本
     */
    private String version = null;
    
    /**
     * API的编号
     */
    private String apiNo;
    
    /**
     * 服务方法是否已经过期
     */
    private boolean obsoleted = false;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getApiNo() {
		return apiNo;
	}

	public void setApiNo(String apiNo) {
		this.apiNo = apiNo;
	}

	public boolean isObsoleted() {
		return obsoleted;
	}

	public void setObsoleted(boolean obsoleted) {
		this.obsoleted = obsoleted;
	}   
}