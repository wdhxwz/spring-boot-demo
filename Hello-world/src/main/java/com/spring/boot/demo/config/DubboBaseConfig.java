package com.spring.boot.demo.config;

import org.springframework.context.annotation.Bean;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;

/**
 * Dubbo相关配置
 * 
 * @author wdhcxx
 *
 */
public class DubboBaseConfig {

	/**
	 * 配置注册中心
	 * 
	 * @return
	 */
	@Bean
	public RegistryConfig registry() {
		RegistryConfig registryConfig = new RegistryConfig();
		registryConfig.setAddress("127.0.0.1:2181");
		registryConfig.setProtocol("zookeeper");

		return registryConfig;
	}

	/**
	 * 配置应用名称
	 * 
	 * @return
	 */
	@Bean
	public ApplicationConfig application() {
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName("testApp");

		return applicationConfig;
	}

	/**
	 * 配置监控中心
	 * 
	 * @return
	 */
	public MonitorConfig monitorConfig() {
		MonitorConfig mc = new MonitorConfig();
		mc.setProtocol("registry");

		return mc;
	}

//	public ReferenceConfig<?> referenceConfig() {
//		ReferenceConfig<?> rc = new ReferenceConfig();
//		rc.setMonitor(monitorConfig());
//
//		return rc;
//	}

	@Bean
	public ProtocolConfig protocol() {
		ProtocolConfig protocolConfig = new ProtocolConfig();
		protocolConfig.setPort(20880);
		
		return protocolConfig;
	}

	@Bean
	public ProviderConfig provider() {
		ProviderConfig providerConfig = new ProviderConfig();
		providerConfig.setMonitor(monitorConfig());
		return providerConfig;
	}
}
