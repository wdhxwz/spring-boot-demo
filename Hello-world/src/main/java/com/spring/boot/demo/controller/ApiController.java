package com.spring.boot.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.demo.apirouter.ApiRequest;
import com.spring.boot.demo.apirouter.ApiRouter;
import com.spring.boot.demo.apirouter.ServiceResult;
import com.spring.boot.demo.apiservice.ApiRequest2001;
import com.spring.boot.demo.utils.JsonUtils;

@RestController
@RequestMapping(value = { "/api" }, method = { RequestMethod.POST })
public class ApiController {
	@Autowired
	ApiRouter apiRouter;

	@RequestMapping()
	public ServiceResult request(HttpServletRequest webRequest, @RequestBody(required = false) String data) {
		ApiRequest bindObject = BeanUtils.instantiateClass(ApiRequest2001.class);
		bindObject = JsonUtils.toObject(data, ApiRequest2001.class);

		ServletRequestDataBinder dataBinder = new ServletRequestDataBinder(bindObject, "bindObject");
		// dataBinder.setConversionService(getDefaultConversionService());
		dataBinder.setValidator(getValidator());
		// dataBinder.bind(webRequest);
		dataBinder.validate();

		BindingResult bindingResult = dataBinder.getBindingResult();
		System.out.println("hehe:" + JsonUtils.toJson(bindObject));
		System.out.println(JsonUtils.toJson(bindingResult.getAllErrors()));
		for (ObjectError objectError : bindingResult.getAllErrors()) {
			if(objectError instanceof FieldError){
				FieldError fieldError = (FieldError)objectError;
				System.out.println(JsonUtils.toJson(fieldError));
			}
		}

		return apiRouter.service(data);
	}

	private Validator getValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();

		return localValidatorFactoryBean;
	}
}