package com.spring.boot.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.demo.apirouter.ApiRouter;
import com.spring.boot.demo.apirouter.ServiceResult;

@RestController
@RequestMapping(value = { "/api" }, method = { RequestMethod.POST })
public class ApiController {
	@Autowired
	ApiRouter apiRouter;

	@RequestMapping()
	public ServiceResult request(@RequestBody(required = false) String data) {
		return apiRouter.service(data);
	}
}