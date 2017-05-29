package com.spring.boot.demo.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/template")
public class TemplateController {

	@RequestMapping("/thymeleaf")
	public String sayHello(Map<String, Object> map) {
		map.put("hello", "say hello from thymeleaf template");

		return "hello";
	}

	@RequestMapping("/helloFtl")
	public String helloFtl(Map<String, Object> map) {
		map.put("hello", "say hello from FreeMarker template");

		return "helloFtl";
	}

	@RequestMapping("/helloJsp")
	public String helloJsp(Map<String, Object> map) {
		map.put("hello", "say hello from thymeleaf template");
		
		return "hello";
	}
}
