package com.bridgeLabz.RedisTemplateProject.services;

import java.util.Map;

import com.bridgeLabz.RedisTemplateProject.model.User;
import com.bridgeLabz.RedisTemplateProject.myExeption.InternalServerError;

public interface RedisService {
	public int addUser(User user) throws InternalServerError;
	public Map<String, String> getUserData(String value);
	public String getKey(String key);
	public Long deleteKey(String key);
}
