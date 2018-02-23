package com.bridgeLabz.RedisTemplateProject.redisServices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ScanOptions.ScanOptionsBuilder;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.bridgeLabz.RedisTemplateProject.model.User;

@Service
public class RedisServiceImpl implements RedisService {
	
	@Autowired
	private StringRedisTemplate StringredisTemplate;
	
	/*@Autowired
	private RedisTemplate<String, String> redisTemplate;*/
	
	private final String KEY = "UserData";
	
	@Override
	public long addUser(User user) {

		SetOperations<String, String> redisSet = StringredisTemplate.opsForSet();
		long  response = redisSet.add(KEY+user.getCity(), "email:"+user.getEmail(), "contact:"+user.getContact(), "name:"+user.getName(), "address:"+user.getAddress());
		return response;
	}
	
	@Override
	public List<String> getUserData(String value, String city) {
		List<String> list = new ArrayList<>();
		ScanOptionsBuilder builder = ScanOptions.scanOptions();
		builder.match(value);
		builder.count(10);
		SetOperations<String, String> redisSet = StringredisTemplate.opsForSet();
		Cursor<String> cursor = redisSet.scan(KEY+city, builder.build());
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
