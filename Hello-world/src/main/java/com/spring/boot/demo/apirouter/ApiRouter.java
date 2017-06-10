package com.spring.boot.demo.apirouter;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

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

	public ApiRegister getApiRegister() {
		return apiRegister;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void startup() {
		if (logger.isInfoEnabled()) {
			logger.info("开始启动ApiRouter...");
		}
		Assert.notNull(this.applicationContext, "Spring上下文不能为空");
		apiRegister = new DefaultApiRegister(applicationContext);

		if (logger.isInfoEnabled()) {
			logger.info("ApiRouter启动成功！");
		}
	}

	/**
	 * 执行接口
	 */
	public ServiceResult service(String requestData) {
		ServiceResult serviceResult = null;
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

		ApiRequest apiRequest = JsonUtils.toObject(requestData, ApiRequest.class);
		if (apiRequest == null) {
			serviceResult = new ServiceResult();
			serviceResult.setCode("402");
			serviceResult.setMessage("请求数据json序列化后对象为空");

			return serviceResult;
		}

		// 验证请求头
		RequestHead requestHead = apiRequest.getRequestHead();
		serviceResult = validRequestHead(requestHead);
		if (serviceResult != null) {
			return serviceResult;
		}

		String apiNo = requestHead.getApiNo();
		String version = requestHead.getVersion();

		// 验证apiNo是否存在
		if (!apiRegister.isValidMethod(apiNo)) {
			logger.info("apiNo无效");
			serviceResult = new ServiceResult();
			serviceResult.setCode("402");
			serviceResult.setMessage("apiNo无效");

			return serviceResult;
		}

		// 验证接口版本号是否正确
		if (!apiRegister.isValidVersion(apiNo, version)) {
			logger.info("接口不存在该版本号");
			serviceResult = new ServiceResult();
			serviceResult.setCode("402");
			serviceResult.setMessage("接口不存在该版本号");

			return serviceResult;
		}

		// 验证接口对应的版本号是否失效
		if (apiRegister.isVersionObsoleted(apiNo, version)) {
			logger.info("接口对应的版本号已失效");

			serviceResult = new ServiceResult();
			serviceResult.setCode("402");
			serviceResult.setMessage("接口对应的版本号已失效");

			return serviceResult;
		}

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

	/**
	 * 验证请求头
	 * 
	 * @param requestHead
	 *            请求头
	 * @return
	 */
	private ServiceResult validRequestHead(RequestHead requestHead) {
		ServiceResult validResult = null;
		if (requestHead == null) {
			validResult = new ServiceResult();
			validResult.setData("请求头为空");

			return validResult;
		}
		if (StringUtils.isEmpty(requestHead.getAppNo())) {
			logger.info("appNo为空");

			validResult = new ServiceResult();
			validResult.setData("appNo为空");

			return validResult;
		}

		// 验证appNo是否正确(目前可先配置到配置文件)

		if (StringUtils.isEmpty(requestHead.getApiNo())) {
			logger.info("apiNo为空");

			validResult = new ServiceResult();
			validResult.setData("apiNo为空");

			return validResult;
		}

		// 版本号暂时不做验证
		if (StringUtils.isEmpty(requestHead.getVersion())) {
			logger.info("版本号为空");

			validResult = new ServiceResult();
			validResult.setData("版本号为空");

			return validResult;
		}

		return validResult;
	}

}