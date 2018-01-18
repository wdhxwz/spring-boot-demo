package com.wangdh.springboot.quickstart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Hello world!
 */
@EnableAutoConfiguration
@ComponentScan
@Configuration
public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        // 访问应用参数
        logger.info("打印应用参数开始");
        for (String arg : args) {
            logger.info(arg);
        }
        logger.info("打印应用参数结束");

        // 运行SpringApplication
        // SpringApplication.run(App.class,args);

        /**
         * 自定义SpringBootApplication
         * <br/> 传递给 SpringApplication 的构造器参数将作为spring beans的配置源
         */
        SpringApplication app = new SpringApplication(App.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.setWebEnvironment(true);
        app.run(args);
        app.setAddCommandLineProperties(false);

        /**
         * 流式操作
         */
//        new SpringApplicationBuilder()
//                .sources(App.class) // 父上下文
//                .bannerMode(Banner.Mode.OFF)
//                .web(true)
//                .run(args);
    }
}
