package com.bridgeLabz.RedisTemplateProject.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.bridgeLabz.RedisTemplateProject.model.User;
import com.bridgeLabz.RedisTemplateProject.myExeption.InternalServerError;

@Service
public class RedisServiceImpl implements RedisService{

	@Autowired
	StringRedisTemplate redisTemplate;

	private final String KEY = "UserData";
	private final String NAMEHASHKEY = "NAME";
	private final String CONTACTHASHKEY = "CONTACT";
	public int addUser(User user) throws InternalServerError {

		HashOperations<String, String, String> hashMap = redisTemplate.opsForHash();
		hashMap.put(KEY, user.getName(),NAMEHASHKEY);
		hashMap.put(KEY, user.getContact(), CONTACTHASHKEY);
		return 1;
	}

	public Map<String, String> getUserData(String value) {
		HashOperations<String, String, String> hashMap = redisTemplate.opsForHash();
		Map<String, String> dataMap = hashMap.entries(value);
		return dataMap;

	}

	public String getKey(String key) {
		HashOperations<String, String, String> hashMap = redisTemplate.opsForHash();
		return hashMap.get(KEY, key);
	}

	public Long deleteKey(String key) {
		HashOperations<String, String, String> hashMap = redisTemplate.opsForHash();
		return hashMap.delete(KEY, key);
	}

}
