package com.bridgeLabz.RedisTemplateProject.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
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
		List<String> list = new ArrayList<>();
		value  = "*:" + value + "*";
		Cursor<String> cursor = redisServiceImpl.getUserData(value, city);
		long cursorPosition = -1;
		while(cursorPosition != 0){
			cursorPosition = cursor.getPosition();
			while (cursor.hasNext()) {
				list.add(cursor.next());
			}
		}
		return list;
	}
	
	

}
