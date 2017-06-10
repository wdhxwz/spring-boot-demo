/**
 * 
 */
package com.spring.boot.demo.apirouter;

import java.util.Map;

/**
 * 接口路由服务注册器
 * 
 * @author wdhcxx
 *
 */
public interface ApiRegister {
    /**
     * 注册一个服务处理器
     *
     * @param apiNo 接口编号
     * @param version 接口版本号
     * @param serviceMethodHandler 接口处理器
     */
    void addServiceMethod(String apiNo, String version, ServiceMethodHandler serviceMethodHandler);

    /**
     * 获取服务处理器
     *
     * @param apiNo 接口编号
     * @param version 接口版本号
     * @return
     */
    ServiceMethodHandler getServiceMethodHandler(String apiNo, String version);

    /**
     * 是否是合法的服务方法
     *
     * @param apiNo 接口编号
     * @return
     */
    boolean isValidMethod(String apiNo);

    /**
     * 是否存在对应的服务方法的版本号
     *
     * @param apiNo 接口编号
     * @param version 接口版本号
     * @return
     */
    boolean isValidVersion(String apiNo, String version);


    /**
     * 服务方法的版本是否已经弃用
     *
     * @param apiNo 接口编号
     * @param version 接口版本号
     * @return
     */
    boolean isVersionObsoleted(String apiNo, String version);

    /**
     * 获取所有的处理器列表
     *
     * @return
     */
    Map<String, ServiceMethodHandler> getAllServiceMethodHandlers();
}