package com.spring.boot.demo.apirouter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Service;

/**
 * 标记在类上，使类成为服务类，默认已经加了 {@link Service} 注册，会自动注册成为一个Spring Bean
 * @author wdhcxx
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface ServiceBean {
    String value() default "";
    
    /**
     * 该方法所对应的版本号，对应version请求参数的值，版本为空，表示不进行版本限定
     *
     * @return
     */
    String version() default "";
}