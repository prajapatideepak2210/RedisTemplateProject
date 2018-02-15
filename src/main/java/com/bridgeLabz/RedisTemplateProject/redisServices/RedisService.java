package com.bridgeLabz.RedisTemplateProject.redisServices;

import org.springframework.data.redis.core.Cursor;

import com.bridgeLabz.RedisTemplateProject.model.User;

public interface RedisService {
	public long addUser(User user) ;
	public Cursor<String> getUserData(String value, String city);
	/*public String getKey(String key);
	public Long deleteKey(String key);*/
}
