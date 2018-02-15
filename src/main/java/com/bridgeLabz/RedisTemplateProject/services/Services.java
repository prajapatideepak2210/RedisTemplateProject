package com.bridgeLabz.RedisTemplateProject.services;

import java.util.List;

import com.bridgeLabz.RedisTemplateProject.model.User;

public interface Services {
	
	public long addUser(User user);
	
	public List<String> getUserData(String value, String city);


}
