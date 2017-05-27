package com.spring.boot.demo.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public void defaultErrorHandler(HttpServletRequest req, Exception e) {

		e.printStackTrace();

		System.out.println("GlobalDefaultExceptionHandler.defaultErrorHandler()");
	}
}