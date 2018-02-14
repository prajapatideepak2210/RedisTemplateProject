package com.bridgeLabz.RedisTemplateProject.services;

import java.util.List;

import com.bridgeLabz.RedisTemplateProject.model.User;
import com.bridgeLabz.RedisTemplateProject.myExeption.InternalServerError;

public interface RedisService {
	public long addUser(User user) throws InternalServerError;
	public List<String> getUserData(String value, String city);
	/*public String getKey(String key);
	public Long deleteKey(String key);*/
}
