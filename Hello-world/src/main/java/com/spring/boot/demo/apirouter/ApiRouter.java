package com.spring.boot.demo.apirouter;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

import com.spring.boot.demo.utils.JsonUtils;

/**
 * 接口路由
 * 
 * @author wdhcxx
 *
 */
public class ApiRouter implements ApplicationContextAware {
	private static Logger logger = LoggerFactory.getLogger(ApiRouter.class);

	/**
	 * Spring容器
	 */
	private ApplicationContext applicationContext;

	/**
	 * 接口注册器
	 */
	private ApiRegister apiRegister;

	/**
	 * 请求头验证器
	 */
	private RequestHeadValidator requestHeadValidator;

	public ApiRegister getApiRegister() {
		return apiRegister;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * 启动ApiRouter
	 */
	public void startup() {
		if (logger.isInfoEnabled()) {
			logger.info("开始启动ApiRouter...");
		}
		Assert.notNull(this.applicationContext, "Spring上下文不能为空");
		apiRegister = new DefaultApiRegister(applicationContext);
		if (null == requestHeadValidator) {
			requestHeadValidator = new DefaultRequestHeadValidator(apiRegister);
		}

		if (logger.isInfoEnabled()) {
			logger.info("ApiRouter启动成功！");
		}
	}

	/**
	 * 执行接口
	 */
	public ServiceResult service(String requestData) {
		ServiceResult serviceResult = null;
		
		// 请求的时候,参数为"",这里接收到的数据为"\"\"",因此不为空
		if (logger.isInfoEnabled()) {
			logger.info("请求的数据：" + requestData);
		}
		
		if (StringUtils.isEmpty(requestData)) {
			serviceResult = new ServiceResult();
			serviceResult.setCode("401");
			serviceResult.setMessage("请求数据为空");

			return serviceResult;
		}

		// Url decode
		if (requestData.indexOf("%") > -1) {
			try {
				requestData = URLDecoder.decode(requestData, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.info("url解码时遇到未知编码方式");
			}
		}

		// 解析出requestHead
		ApiRequest apiRequest = null;
		try {
			apiRequest = JsonUtils.toObject(requestData, ApiRequest.class);
		} catch (Exception e) {
			logger.error("对象序列化失败", e);
		}
		if (apiRequest == null) {
			logger.info("请求数据json序列化后对象为空");
			serviceResult = new ServiceResult();
			serviceResult.setCode("402");
			serviceResult.setMessage("请求数据格式错误");

			return serviceResult;
		}

		// 验证请求头
		RequestHead requestHead = apiRequest.getRequestHead();
		serviceResult = requestHeadValidator.valid(requestHead);
		if (serviceResult != null) {
			return serviceResult;
		}

		String apiNo = requestHead.getApiNo();
		String version = requestHead.getVersion();
		ServiceMethodHandler serviceMethodHandler = apiRegister.getServiceMethodHandler(apiNo, version);

		if (serviceMethodHandler.isHandlerMethodWithParameter()) {
			try {
				serviceResult = (ServiceResult) serviceMethodHandler.getHandlerMethod().invoke(
						serviceMethodHandler.getHandler(),
						JsonUtils.toObject(requestData, serviceMethodHandler.getRequestType()));
			} catch (IllegalAccessException e) {
			} catch (IllegalArgumentException e) {
			} catch (InvocationTargetException e) {
			}
		} else {
			try {
				serviceResult = (ServiceResult) serviceMethodHandler.getHandlerMethod()
						.invoke(serviceMethodHandler.getHandler());
			} catch (IllegalAccessException e) {
			} catch (IllegalArgumentException e) {
			} catch (InvocationTargetException e) {
			}
		}

		return serviceResult;
	}
}