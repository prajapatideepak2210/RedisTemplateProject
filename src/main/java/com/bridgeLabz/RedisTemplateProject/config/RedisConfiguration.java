package com.bridgeLabz.RedisTemplateProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.bridgeLabz.RedisTemplateProject.model.User;

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
	
	/*@Bean
	public JedisCluster jedisCluster() {
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		jedisClusterNodes.add(new HostAndPort("localhost", 10000));
		JedisClusterConnection jedisClusterConnection = (JedisClusterConnection) jedisConnectionFactory().getClusterConnection();
		JedisCluster jedisCluster = jedisClusterConnection.getNativeConnection();
		JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes);
		return jedisCluster;
	}*/

}
