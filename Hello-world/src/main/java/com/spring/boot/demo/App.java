package com.spring.boot.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication 
@ServletComponentScan // 加入Servlet
// ,"classpath:dubbo-consumer.xml"
@ImportResource(locations = { "classpath:application-bean.xml" }) // 加入xml配置
@MapperScan(basePackages = "com.spring.boot.demo.mapper") // 扫描mapper
public class App {
	/*
	 * @Bean public ServletRegistrationBean MyServlet(){ //
	 * 注册Servlet,并配置该Servlet的访问路径 return new ServletRegistrationBean(new
	 * MyServlet(),"/myservlet/*"); }
	 */

	/**
	 * 限制文件上传大小
	 * 
	 * @return
	 */
	/*
	 * @Bean public MultipartConfigElement multipartConfigElement() {
	 * MultipartConfigFactory factory = new MultipartConfigFactory(); //
	 * 设置文件大小限制 ,超了，页面会抛出异常信息，这时候就需要进行异常信息的处理了; factory.setMaxFileSize("128KB");
	 * // KB,MB
	 * 
	 * // 设置总上传数据总大小 factory.setMaxRequestSize("256KB");
	 * 
	 * // Sets the directory location where files will be stored. //
	 * factory.setLocation("路径地址");
	 * 
	 * return factory.createMultipartConfig(); }
	 */

	/**
	 * 程序扫描的包默认是执行类所在包及其子包
	 */
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}