/**
 * 
 */
package com.spring.boot.demo.apirouter;

import org.apache.commons.lang3.StringUtils;

/**
 * 抽象的请求头验证类
 * 
 * @author wdhcxx
 *
 */
public abstract class AbstractRequestHeadValidator implements RequestHeadValidator{

	/**
	 * 验证请求头
	 * 
	 * @param requestHead
	 * @return
	 */
	public ServiceResult valid(RequestHead requestHead) {
		if (null == requestHead) {
			return ServiceResult.build("401", "请求头为空");
		}

		if (StringUtils.isBlank(requestHead.getAppKey())) {
			return ServiceResult.build("401", "AppKey为空");
		}

		if (StringUtils.isBlank(requestHead.getApiNo())) {
			return ServiceResult.build("401", "ApiNo为空");
		}

		if (StringUtils.isBlank(requestHead.getVersion())) {
			return ServiceResult.build("401", "Version为空");
		}
		
		if(!isValidAppkey(requestHead.getAppKey())){
			return ServiceResult.build("402", "无效的AppKey");
		}

		if(!isValidApiNo(requestHead.getApiNo())){
			return ServiceResult.build("402", "无效的ApiNo");
		}
		
		if(!isValidVersion(requestHead.getApiNo(), requestHead.getVersion())){
			return ServiceResult.build("402", "无效的版本号或改版本号已失效");
		}
		
		return null;
	}

	/**
	 * 验证appkey是否有效
	 * 
	 * @param appkey
	 *            appkey
	 * @return
	 */
	protected abstract boolean isValidAppkey(String appkey);

	/**
	 * 验证版本号
	 * 
	 * @param version
	 * @param apiNo
	 * @return
	 */
	protected abstract boolean isValidVersion(String apiNo, String version);

	/**
	 * 验证接口编号
	 * 
	 * @param apiNo
	 * @return
	 */
	protected abstract boolean isValidApiNo(String apiNo);
}