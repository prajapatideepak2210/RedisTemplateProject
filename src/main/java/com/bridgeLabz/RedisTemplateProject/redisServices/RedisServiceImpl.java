package com.bridgeLabz.RedisTemplateProject.redisServices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ScanOptions.ScanOptionsBuilder;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {
	
	@Autowired
	private StringRedisTemplate StringredisTemplate;
	
	@Override
	public long add(String key, String... values) {

		SetOperations<String, String> redisSet = StringredisTemplate.opsForSet();
		long  response = redisSet.add(key, values);
		return response;
	}
	
	@Override
	public List<String> search(String key, String value) {
		List<String> list = new ArrayList<>();
		ScanOptionsBuilder builder = ScanOptions.scanOptions();
		builder.match(value);
		builder.count(10);
		SetOperations<String, String> redisSet = StringredisTemplate.opsForSet();
		Cursor<String> cursor = redisSet.scan(key, builder.build());
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
