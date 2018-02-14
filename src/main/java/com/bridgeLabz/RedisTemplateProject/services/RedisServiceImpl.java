package com.bridgeLabz.RedisTemplateProject.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ScanOptions.ScanOptionsBuilder;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.bridgeLabz.RedisTemplateProject.model.User;
import com.bridgeLabz.RedisTemplateProject.myExeption.InternalServerError;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private StringRedisTemplate redisTemplate;
	
	/*@Autowired
	private JedisCluster jedisCluster;*/

	private final String KEY = "UserData";
	/*private final String NAMESETKEY = "name";
	private final String CONTACTSETKEY = "contact";
	private final String EMAILSETKEY = "email";
	private final String CITYSETKEY = "city";*/
	
	public long addUser(User user) throws InternalServerError {

		SetOperations<String, String> redisSet = redisTemplate.opsForSet();
		long  response = redisSet.add(KEY+user.getCity(), "email:"+user.getEmail(), "contact:"+user.getContact(), "name:"+user.getName());
		return response;
	}

	public List<String> getUserData(String value, String city) {
		ScanOptionsBuilder builder = ScanOptions.scanOptions();
		List<String> list = new ArrayList<>();
		builder.match("*:" + value + "*");
		builder.count(10);
		long cursorPosition = -1;
		while(cursorPosition != 0){
			SetOperations<String, String> redisSet = redisTemplate.opsForSet();
			Cursor<String> dataMap = redisSet.scan(KEY+city, builder.build());
			cursorPosition = dataMap.getPosition();
			while (dataMap.hasNext()) {
				list.add(dataMap.next());
			}
		}
		return list;
	}

	/*public String getKey(String key) {
		HashOperations<String, String, String> hashMap = redisTemplate.opsForHash();
		return hashMap.get(KEY, key);
		Set<String> set = redisTemplate.keys(KEY);
		for (String string : set) {
			System.out.println(string);
		}
		return "voila";

	}

	public Long deleteKey(String key) {
		HashOperations<String, String, String> hashMap = redisTemplate.opsForHash();
		return hashMap.delete(KEY, key);
	}

	public String pipeline(String key){
		
	    return "jmhdj,hgjhfgjk";
	}*/

}
