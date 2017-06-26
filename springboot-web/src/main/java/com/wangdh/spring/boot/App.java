package com.wangdh.spring.boot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ImportResource;

import com.wangdh.spring.boot.config.bean.Author;

@SpringBootApplication
@ImportResource(locations = {})
@EnableConfigurationProperties(value = { Author.class })
public class App {
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(App.class);
		springApplication.setBannerMode(Banner.Mode.OFF);
		springApplication.run(args);      
	}
}