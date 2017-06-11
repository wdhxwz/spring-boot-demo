package com.spring.boot.demo.apirouter;

/**
 * 默认的请求头验证器
 * 
 * @author wdhcxx
 *
 */
public class DefaultRequestHeadValidator extends AbstractRequestHeadValidator {
	public DefaultRequestHeadValidator(ApiRegister apiRegister){
		this.apiRegister = apiRegister;
	}
	
	@Override
	protected boolean isValidAppkey(String appkey) {
		// TODO appKey是由系统颁发,目前量少,可以考虑存储在配置文件中,后续可存储在数据库中
		
		return true;
	}

	@Override
	protected boolean isValidVersion(String apiNo, String version) {
		return apiRegister.isValidVersion(apiNo, version) || !apiRegister.isVersionObsoleted(apiNo, version);
	}

	@Override
	protected boolean isValidApiNo(String apiNo) {
		return apiRegister.isValidMethod(apiNo);
	}

	/**
	 * 接口注册器
	 */
	private ApiRegister apiRegister;
}