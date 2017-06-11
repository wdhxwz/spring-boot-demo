package com.spring.boot.demo.apirouter;

/**
 * 验证请求头接口
 * @author wdhcxx
 *
 */
public interface RequestHeadValidator {
	/**
	 * 验证请求头
	 * @param requestHead
	 * @return
	 */
	ServiceResult valid(RequestHead requestHead);
}
