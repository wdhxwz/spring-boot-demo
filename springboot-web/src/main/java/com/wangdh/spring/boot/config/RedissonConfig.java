package com.wangdh.spring.boot.config;

import java.io.IOException;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * redisson配置
 * @author wangdh
 * 2017年6月21日下午2:09:49
 */
@Configuration
public class RedissonConfig {

	@Bean
	public RedissonClient redissonClient() throws IOException{
		RedissonClient redisson = Redisson.create(Config.fromYAML(new ClassPathResource("redis-single.yaml").getFile()));
		
		return redisson;
	}
	
	@Bean
    CacheManager cacheManager(RedissonClient redissonClient) {
		return new RedissonSpringCacheManager(redissonClient, "classpath:/cache-config.yaml");
    }
}
