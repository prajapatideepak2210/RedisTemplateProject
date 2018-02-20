package com.bridgeLabz.RedisTemplateProject.redisServices;

import java.util.List;

import com.bridgeLabz.RedisTemplateProject.model.User;

public interface RedisService {
	public long addUser(User user) ;
	public List<String> getUserData(String value, String city);
	/*public String getKey(String key);
	public Long deleteKey(String key);*/
}
