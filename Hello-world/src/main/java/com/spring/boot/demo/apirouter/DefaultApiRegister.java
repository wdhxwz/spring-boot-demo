package com.spring.boot.demo.apirouter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import com.spring.boot.demo.utils.JsonUtils;

/**
 * 默认的路由接口注册实现
 * 
 * @author wdhcxx
 *
 */
public class DefaultApiRegister implements ApiRegister {

	private static Logger logger = LoggerFactory.getLogger(DefaultApiRegister.class);

	// 服务方法集合
	private final Set<String> serviceMethods = new HashSet<String>();

	// 接口处理器緩存
	private final Map<String, ServiceMethodHandler> serviceHandlerMap = new HashMap<String, ServiceMethodHandler>();

	public DefaultApiRegister(ApplicationContext context) {
		registerServiceBeanFromContext(context);
	}

	/**
	 * 根据api编号和版本号获取接口对应的处理器
	 * 
	 * @param apiNo
	 *            接口编号
	 * @param version
	 *            版本号
	 * @return
	 */
	@Override
	public ServiceMethodHandler getServiceMethodHandler(String apiNo, String version) {
		return serviceHandlerMap.get(ServiceMethodHandler.methodWithVersion(apiNo, version));
	}

	/**
	 * 判断是否存在指定apiNo的服务接口
	 * 
	 * @param apiNo
	 *            接口编号
	 * @return
	 */
	@Override
	public boolean isValidMethod(String apiNo) {
		return serviceMethods.contains(apiNo);
	}

	/**
	 * 判断所调用的apiNo的版本号是否正确
	 * 
	 * @param apiNo
	 *            接口编号
	 * @param version
	 *            接口版本号
	 * @return
	 */
	@Override
	public boolean isValidVersion(String apiNo, String version) {
		return serviceHandlerMap.containsKey(ServiceMethodHandler.methodWithVersion(apiNo, version));
	}

	/**
	 * 验证版本号是否已不可用,需要先调用 isValidVersion
	 * 
	 * @param apiNo
	 *            接口编号
	 * @param version
	 *            接口版本号
	 * @return
	 */
	@Override
	public boolean isVersionObsoleted(String apiNo, String version) {
		String key = ServiceMethodHandler.methodWithVersion(apiNo, version);

		return serviceHandlerMap.get(key).getServiceMethodDefinition().isObsoleted();
	}

	/**
	 * 获取所有的处理器列表
	 *
	 * @return
	 */
	@Override
	public Map<String, ServiceMethodHandler> getAllServiceMethodHandlers() {
		return serviceHandlerMap;
	}

	/**
	 * 将接口处理器添加到缓存器中
	 * 
	 * @param apiNo
	 *            接口编号
	 * @param version
	 *            版本号
	 * @param serviceMethodHandler
	 *            接口处理器
	 */
	@Override
	public void addServiceMethod(String apiNo, String version, ServiceMethodHandler serviceMethodHandler) {
		serviceMethods.add(apiNo);
		if (serviceHandlerMap.containsKey(ServiceMethodHandler.methodWithVersion(apiNo, version))) {
			throw new ApiException("重复注册接口");
		}

		serviceHandlerMap.put(ServiceMethodHandler.methodWithVersion(apiNo, version), serviceMethodHandler);
	}

	/**
	 * 从Spring 上下文查找标注{@link ServiceBean}的类，并注册到缓存器中
	 * 
	 * @param context
	 */
	private void registerServiceBeanFromContext(ApplicationContext context) {
		if (logger.isDebugEnabled()) {
			logger.debug("对Spring上下文中的Bean进行扫描，查找Api服务方法: " + context);
		}

		// 获取上下文所有的bean,并遍历所有的bean，将具有@{link ServiceBean}和@{link
		// ServiceMethod}缓存起来
		String[] beanNames = context.getBeanNamesForType(Object.class);
		for (final String beanName : beanNames) {

			// 获取对应bean的类型,然后判断该bean是否打上了@{link ServiceBean}注解
			Class<?> handlerType = context.getType(beanName);
			if (AnnotationUtils.findAnnotation(handlerType, ServiceBean.class) != null) {

				// 对符合条件的bean中的所有方法进行遍历,获取标注了@{link
				// ServiceMethod}注解的方法,作为api处理方法缓存起来
				ReflectionUtils.doWithMethods(handlerType, new ReflectionUtils.MethodCallback() {
					@SuppressWarnings("unchecked")
					@Override
					public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
						ReflectionUtils.makeAccessible(method);
						ServiceMethod serviceMethod = AnnotationUtils.findAnnotation(method, ServiceMethod.class);
						ServiceBean serviceBean = AnnotationUtils.findAnnotation(method.getDeclaringClass(),
								ServiceBean.class);
						ServiceMethodDefinition definition = null;
						if (serviceBean != null) {
							definition = buildServiceMethodDefinition(serviceBean, serviceMethod);
						} else {
							definition = buildServiceMethodDefinition(serviceMethod);
						}

						// 构造处理器
						ServiceMethodHandler serviceMethodHandler = new ServiceMethodHandler();
						serviceMethodHandler.setServiceMethodDefinition(definition);

						// 1.set handler
						serviceMethodHandler.setHandler(context.getBean(beanName)); // handler
						serviceMethodHandler.setHandlerMethod(method); // handler'method

						// 参数处理,接口只能有一个参数,并且需要是@{ApiRequest}获取子类
						if (method.getParameterTypes().length > 1) {
							logger.error(method.getDeclaringClass().getName() + "." + method.getName() + "的入参只能是"
									+ ApiRequest.class.getName() + "或其子类或无入参。");
						} else if (method.getParameterTypes().length == 1) {
							Class<?> paramType = method.getParameterTypes()[0];
							if (!ClassUtils.isAssignable(ApiRequest.class, paramType)) {
								throw new ApiException(method.getDeclaringClass().getName() + "." + method.getName()
										+ "的入参必须是" + ApiRequest.class.getName() + "或其子类");
							}
							boolean apiRequestImplType = !paramType.isAssignableFrom(ApiRequest.class);
							serviceMethodHandler.setApiRequestImplType(apiRequestImplType);
							serviceMethodHandler.setRequestType((Class<? extends ApiRequest>) paramType);
						} else {
							logger.info(method.getDeclaringClass().getName() + "." + method.getName() + "无入参");
						}

						addServiceMethod(definition.getApiNo(), definition.getVersion(), serviceMethodHandler);
						if (logger.isInfoEnabled()) {
							logger.info("注册服务方法：" + method.getDeclaringClass().getCanonicalName() + "."
									+ method.getName() + "(" + serviceMethodHandler.getRequestType().getName()
									+ "),方法信息：" + JsonUtils.toJson(definition));
						}
					}
				}, new ReflectionUtils.MethodFilter() {
					public boolean matches(Method method) {
						return !method.isSynthetic()
								&& AnnotationUtils.findAnnotation(method, ServiceMethod.class) != null;
					}
				});
			}
		}

		// 从父容器进行注册
		if (context.getParent() != null) {
			registerServiceBeanFromContext(context.getParent());
		}
		if (logger.isInfoEnabled()) {
			logger.info("共注册了" + serviceHandlerMap.size() + "个服务方法");
		}
	}

	private ServiceMethodDefinition buildServiceMethodDefinition(ServiceBean serviceBean, ServiceMethod serviceMethod) {
		ServiceMethodDefinition definition = new ServiceMethodDefinition();
		definition.setVersion(serviceBean.version());

		// 版本号以@{ServiceMethod}注解为主
		if (StringUtils.hasText(serviceMethod.version())) {
			definition.setVersion(serviceMethod.version());
		}
		definition.setApiNo(serviceMethod.apiNo());
		definition.setObsoleted(ObsoletedType.isObsoleted(serviceMethod.obsoleted()));

		return definition;
	}

	private ServiceMethodDefinition buildServiceMethodDefinition(ServiceMethod serviceMethod) {
		ServiceMethodDefinition definition = new ServiceMethodDefinition();
		definition.setVersion(serviceMethod.version());
		definition.setApiNo(serviceMethod.apiNo());
		definition.setObsoleted(ObsoletedType.isObsoleted(serviceMethod.obsoleted()));

		return definition;
	}
}