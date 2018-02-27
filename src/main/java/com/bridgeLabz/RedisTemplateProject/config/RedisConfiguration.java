package com.bridgeLabz.RedisTemplateProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfiguration {

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
		connectionFactory.setPort(6379);
		connectionFactory.setHostName("localhost");
		return connectionFactory;
	}

	@Bean
	public <K,V> RedisTemplate<K, V> redisTemplate() {
		final RedisTemplate<K, V> template = new RedisTemplate<K, V>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}

	@Bean
	public StringRedisTemplate stringRedisTemplate() {
		StringRedisTemplate redisTemplate = new StringRedisTemplate();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}
	
}
