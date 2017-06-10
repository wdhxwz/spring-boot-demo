package com.spring.boot.demo.apiservice;

import com.spring.boot.demo.apirouter.ObsoletedType;
import com.spring.boot.demo.apirouter.ServiceBean;
import com.spring.boot.demo.apirouter.ServiceMethod;
import com.spring.boot.demo.apirouter.ServiceResult;
import com.spring.boot.demo.utils.JsonUtils;

@ServiceBean
public class ApiService {
	@ServiceMethod(apiNo = "Api2002")
	public ServiceResult getAccount(ApiRequest2002 request2002){
		ServiceResult result = new ServiceResult();
		System.out.println("开始执行Api2002");
		System.out.println(JsonUtils.toJson(request2002));
		
		result.setMessage("请求Api2002成功");
		
		return result;
	}
	
	@ServiceMethod(apiNo = "Api2001",version="1.0",obsoleted = ObsoletedType.YES)
	public ServiceResult getAccountByChannel(ApiRequest2001 request2001){
		ServiceResult result = new ServiceResult();
		System.out.println(JsonUtils.toJson(request2001));
		
		result.setMessage("请求2001成功");
		
		return result;
	}
}