package com.bridgeLabz.RedisTemplateProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeLabz.RedisTemplateProject.model.User;
import com.bridgeLabz.RedisTemplateProject.redisServices.RedisServiceImpl;

@Service
public class ServicesImpl implements Services {
	
	@Autowired
	private RedisServiceImpl redisServiceImpl;

	@Override
	public long addUser(User user) {
		return redisServiceImpl.addUser(user);
		
	}

	@Override
	public List<String> getUserData(String value, String city) {
		value  = "*:*" + value + "*";
		return redisServiceImpl.getUserData(value, city);
	}
	
	

}
