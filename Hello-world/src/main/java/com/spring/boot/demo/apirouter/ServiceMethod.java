package com.spring.boot.demo.apirouter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口处理方法的注解，作用在方法上，使得该方法称为接口处理器，方法所在类必须打上{@link ServiceBean}注解
 * <br/>
 * 版本号默认为1.0
 * <br/>
 * 接口默认为不失效
 * 
 * @author wdhcxx
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceMethod {
	String value() default "";

	/**
	 * 该方法所对应的版本号，对应version请求参数的值，版本为空，表示不进行版本限定
	 *
	 * @return
	 */
	String version() default "1.0";
	
	/**
	 * 接口编号
	 * @return
	 */
	String apiNo() default "";
	
	/**
     * 服务方法是否已经过期，默认不过期
     * @return
     */
    ObsoletedType obsoleted() default  ObsoletedType.DEFAULT;
}
