package com.bridgeLabz.RedisTemplateProject.redisServices;

import java.util.List;

public interface RedisService {
	
	long add(String key, String... values);
	List<String> search(String key, String value);
	
}
