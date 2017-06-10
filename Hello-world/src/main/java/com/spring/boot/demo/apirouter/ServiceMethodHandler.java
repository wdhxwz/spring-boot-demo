package com.spring.boot.demo.apirouter;

import java.lang.reflect.Method;

/**
 * 服务方法处理器
 * 
 * @author wdhcxx
 */
public class ServiceMethodHandler {

	/**
	 * 获取方法的标识，apiNo+#+版本号
	 * 
	 * @param apiNo
	 *            接口编号
	 * @param version
	 *            版本号
	 * @return
	 */
	public static String methodWithVersion(String apiNo, String version) {
		return apiNo + "#" + version;
	}
	
	/**
	 * 接口是否有参数
	 * @return
	 */
	public boolean isHandlerMethodWithParameter() {
        return this.requestType != null;
    }

	// 处理器对象
	private Object handler;

	// 处理器的处理方法
	private Method handlerMethod;

	// api的额外信息，接口编号和版本号等
	private ServiceMethodDefinition serviceMethodDefinition;

	// 处理方法的请求对象类
	private Class<? extends ApiRequest> requestType = ApiRequest.class;

	// 接口参数类型是否是 ApiRequest 实现类
	private boolean apiRequestImplType;

	public Object getHandler() {
		return handler;
	}

	public void setHandler(Object handler) {
		this.handler = handler;
	}

	public Method getHandlerMethod() {
		return handlerMethod;
	}

	public void setHandlerMethod(Method handlerMethod) {
		this.handlerMethod = handlerMethod;
	}

	public ServiceMethodDefinition getServiceMethodDefinition() {
		return serviceMethodDefinition;
	}

	public void setServiceMethodDefinition(ServiceMethodDefinition serviceMethodDefinition) {
		this.serviceMethodDefinition = serviceMethodDefinition;
	}

	public boolean isApiRequestImplType() {
		return apiRequestImplType;
	}

	public void setApiRequestImplType(boolean apiRequestImplType) {
		this.apiRequestImplType = apiRequestImplType;
	}

	public Class<? extends ApiRequest> getRequestType() {
		return requestType;
	}

	public void setRequestType(Class<? extends ApiRequest> requestType) {
		this.requestType = requestType;
	}
}